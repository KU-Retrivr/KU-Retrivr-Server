package retrivr.retrivrspring.presentation.admin.rental.res;

import java.time.LocalDate;
import java.util.List;
import retrivr.retrivrspring.mock.model.RentalMock;

public record AdminOverdueRentalItemPageResponse(
    List<AdminOverdueRentalItem> overdues
) {

  public static AdminOverdueRentalItemPageResponse from(List<RentalMock> rentals) {
    return new AdminOverdueRentalItemPageResponse(
        rentals.stream().map(AdminOverdueRentalItem::from).toList()
    );
  }

  public record AdminOverdueRentalItem(
      Long rentalId,
      String itemName,
      String borrowerName,
      LocalDate rentalDate,
      LocalDate returnDate,
      String status
  ) {
    public static AdminOverdueRentalItem from(RentalMock rental) {
      return new AdminOverdueRentalItem(
          rental.id(),
          rental.itemName(),
          rental.borrowerName(),
          rental.rentalDate(),
          rental.returnDate(),
          rental.status().name()
      );
    }
  }
}
