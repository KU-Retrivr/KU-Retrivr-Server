package retrivr.retrivrspring.presentation.open.rental.res;

import java.time.LocalDate;
import retrivr.retrivrspring.mock.model.RentalMock;

public record PublicRentalDetailResponse(
    Long rentalId,
    String itemName,
    String borrowerName,
    String borrowerPhone,
    String organization,
    String purpose,
    String status,
    LocalDate rentalDate,
    LocalDate returnDate,
    String decisionReason
) {

  public static PublicRentalDetailResponse from(RentalMock rental) {
    return new PublicRentalDetailResponse(
        rental.id(),
        rental.itemName(),
        rental.borrowerName(),
        rental.borrowerPhone(),
        rental.borrowerOrganization(),
        rental.purpose(),
        rental.status().name(),
        rental.rentalDate(),
        rental.returnDate(),
        rental.decisionReason()
    );
  }
}
