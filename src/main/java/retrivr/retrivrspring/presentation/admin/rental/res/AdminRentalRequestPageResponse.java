package retrivr.retrivrspring.presentation.admin.rental.res;

import java.time.LocalDate;
import java.util.List;
import retrivr.retrivrspring.mock.model.RentalMock;

public record AdminRentalRequestPageResponse(
    List<AdminRentalRequestSummary> requests
) {

  public static AdminRentalRequestPageResponse from(List<RentalMock> rentals) {
    return new AdminRentalRequestPageResponse(
        rentals.stream().map(AdminRentalRequestSummary::from).toList()
    );
  }

  public record AdminRentalRequestSummary(
      Long rentalId,
      String itemName,
      String borrowerName,
      String status,
      LocalDate rentalDate,
      LocalDate returnDate
  ) {
    public static AdminRentalRequestSummary from(RentalMock rental) {
      return new AdminRentalRequestSummary(
          rental.id(),
          rental.itemName(),
          rental.borrowerName(),
          rental.status().name(),
          rental.rentalDate(),
          rental.returnDate()
      );
    }
  }
}
