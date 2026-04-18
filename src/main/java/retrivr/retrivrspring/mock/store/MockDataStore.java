package retrivr.retrivrspring.mock.store;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import retrivr.retrivrspring.domain.entity.rental.enumerate.RentalStatus;
import retrivr.retrivrspring.global.error.ApiException;
import retrivr.retrivrspring.mock.model.ItemMock;
import retrivr.retrivrspring.mock.model.OrganizationMock;
import retrivr.retrivrspring.mock.model.RentalMock;

@Component
public class MockDataStore {

  private final Map<Long, OrganizationMock> organizations = new ConcurrentHashMap<>();
  private final Map<Long, ItemMock> items = new ConcurrentHashMap<>();
  private final Map<Long, RentalMock> rentals = new ConcurrentHashMap<>();

  private final AtomicLong itemSequence = new AtomicLong(200L);
  private final AtomicLong rentalSequence = new AtomicLong(5000L);

  @PostConstruct
  void initialize() {
    OrganizationMock org1 = new OrganizationMock(1L, "컴퓨터공학과 학생회");
    OrganizationMock org2 = new OrganizationMock(2L, "소프트웨어융합학회");
    organizations.put(org1.id(), org1);
    organizations.put(org2.id(), org2);

    items.put(101L, new ItemMock(101L, 1L, org1.name(), "빔프로젝터", "회의 및 행사 진행용 빔프로젝터", 3, 2));
    items.put(102L, new ItemMock(102L, 1L, org1.name(), "노트북", "세미나 발표용 노트북", 2, 1));
    items.put(103L, new ItemMock(103L, 2L, org2.name(), "마이크", "행사용 무선 마이크", 5, 5));

    rentals.put(
        5001L,
        new RentalMock(
            5001L,
            101L,
            "빔프로젝터",
            "홍길동",
            "010-1234-5678",
            "컴퓨터공학과",
            "세미나 발표",
            LocalDate.now().plusDays(2),
            LocalDate.now().plusDays(3),
            RentalStatus.REQUESTED
        )
    );

    rentals.put(
        5003L,
        new RentalMock(
            5003L,
            102L,
            "노트북",
            "김철수",
            "010-2222-3333",
            "소프트웨어융합학과",
            "스터디 발표",
            LocalDate.now().minusDays(8),
            LocalDate.now().minusDays(6),
            RentalStatus.RENTED
        )
    );
  }

  public List<OrganizationMock> searchOrganizations(String keyword) {
    String normalized = keyword == null ? "" : keyword.trim().toLowerCase();
    return organizations.values().stream()
        .filter(org -> org.name().toLowerCase().contains(normalized))
        .sorted(Comparator.comparing(OrganizationMock::id))
        .toList();
  }

  public OrganizationMock getOrganization(Long organizationId) {
    OrganizationMock organization = organizations.get(organizationId);
    if (organization == null) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Organization not found.");
    }
    return organization;
  }

  public List<ItemMock> getItemsByOrganization(Long organizationId) {
    getOrganization(organizationId);
    return items.values().stream()
        .filter(item -> item.organizationId().equals(organizationId))
        .sorted(Comparator.comparing(ItemMock::id))
        .toList();
  }

  public ItemMock getItem(Long itemId) {
    ItemMock item = items.get(itemId);
    if (item == null) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Item not found.");
    }
    return item;
  }

  public synchronized RentalMock createRental(
      Long itemId,
      String borrowerName,
      String borrowerPhone,
      String borrowerOrganization,
      String purpose,
      LocalDate rentalDate,
      LocalDate returnDate
  ) {
    ItemMock item = getItem(itemId);
    Long rentalId = rentalSequence.incrementAndGet();
    RentalMock rental = new RentalMock(
        rentalId,
        itemId,
        item.name(),
        borrowerName,
        borrowerPhone,
        borrowerOrganization,
        purpose,
        rentalDate,
        returnDate,
        RentalStatus.REQUESTED
    );
    rentals.put(rentalId, rental);
    return rental;
  }

  public RentalMock getRental(Long rentalId) {
    RentalMock rental = rentals.get(rentalId);
    if (rental == null) {
      throw new ApiException(HttpStatus.NOT_FOUND, "Rental not found.");
    }
    return rental;
  }

  public List<RentalMock> getRequestedRentals() {
    return rentals.values().stream()
        .filter(rental -> rental.status() == RentalStatus.REQUESTED)
        .sorted(Comparator.comparing(RentalMock::id))
        .toList();
  }

  public synchronized RentalMock approveRental(Long rentalId) {
    RentalMock rental = getRental(rentalId);
    if (rental.status() != RentalStatus.REQUESTED) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "Only requested rentals can be approved.");
    }

    ItemMock item = getItem(rental.itemId());
    if (item.availableQuantity() <= 0) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "No available stock for approval.");
    }

    item.decreaseAvailableQuantity();
    rental.approve();
    return rental;
  }

  public synchronized RentalMock rejectRental(Long rentalId, String reason) {
    RentalMock rental = getRental(rentalId);
    if (rental.status() != RentalStatus.REQUESTED) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "Only requested rentals can be rejected.");
    }
    rental.reject(reason);
    return rental;
  }

  public synchronized RentalMock returnRental(Long rentalId) {
    RentalMock rental = getRental(rentalId);
    if (rental.status() != RentalStatus.RENTED) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "Only rented items can be returned.");
    }

    ItemMock item = getItem(rental.itemId());
    item.increaseAvailableQuantity();
    rental.markReturned();
    return rental;
  }

  public synchronized ItemMock createItem(
      Long organizationId,
      String name,
      String description,
      int totalQuantity
  ) {
    OrganizationMock organization = getOrganization(organizationId);
    Long itemId = itemSequence.incrementAndGet();
    ItemMock item = new ItemMock(
        itemId,
        organizationId,
        organization.name(),
        name,
        description,
        totalQuantity,
        totalQuantity
    );
    items.put(itemId, item);
    return item;
  }

  public List<ItemMock> getAllItems() {
    return items.values().stream()
        .sorted(Comparator.comparing(ItemMock::id))
        .toList();
  }

  public List<RentalMock> getOverdueRentals() {
    LocalDate today = LocalDate.now();
    return rentals.values().stream()
        .filter(rental -> rental.status() == RentalStatus.RENTED)
        .filter(rental -> rental.returnDate().isBefore(today))
        .sorted(Comparator.comparing(RentalMock::id))
        .toList();
  }

  public List<RentalMock> getAllRentals() {
    return new ArrayList<>(rentals.values());
  }
}
