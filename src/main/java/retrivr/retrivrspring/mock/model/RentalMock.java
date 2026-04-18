package retrivr.retrivrspring.mock.model;

import java.time.LocalDate;
import retrivr.retrivrspring.domain.entity.rental.enumerate.RentalStatus;

public class RentalMock {

  private final Long id;
  private final Long itemId;
  private final String itemName;
  private final String borrowerName;
  private final String borrowerPhone;
  private final String borrowerOrganization;
  private final String purpose;
  private final LocalDate rentalDate;
  private final LocalDate returnDate;
  private RentalStatus status;
  private String decisionReason;

  public RentalMock(
      Long id,
      Long itemId,
      String itemName,
      String borrowerName,
      String borrowerPhone,
      String borrowerOrganization,
      String purpose,
      LocalDate rentalDate,
      LocalDate returnDate,
      RentalStatus status
  ) {
    this.id = id;
    this.itemId = itemId;
    this.itemName = itemName;
    this.borrowerName = borrowerName;
    this.borrowerPhone = borrowerPhone;
    this.borrowerOrganization = borrowerOrganization;
    this.purpose = purpose;
    this.rentalDate = rentalDate;
    this.returnDate = returnDate;
    this.status = status;
  }

  public Long id() {
    return id;
  }

  public Long itemId() {
    return itemId;
  }

  public String itemName() {
    return itemName;
  }

  public String borrowerName() {
    return borrowerName;
  }

  public String borrowerPhone() {
    return borrowerPhone;
  }

  public String borrowerOrganization() {
    return borrowerOrganization;
  }

  public String purpose() {
    return purpose;
  }

  public LocalDate rentalDate() {
    return rentalDate;
  }

  public LocalDate returnDate() {
    return returnDate;
  }

  public RentalStatus status() {
    return status;
  }

  public String decisionReason() {
    return decisionReason;
  }

  public void approve() {
    this.status = RentalStatus.RENTED;
    this.decisionReason = null;
  }

  public void reject(String reason) {
    this.status = RentalStatus.REJECTED;
    this.decisionReason = reason;
  }

  public void markReturned() {
    this.status = RentalStatus.RETURNED;
  }
}
