package retrivr.retrivrspring.presentation.open.rental.res;

import retrivr.retrivrspring.mock.model.RentalMock;

public record PublicRentalCreateResponse(
    Long rentalId,
    Long itemId,
    String status,
    String message
) {

  public static PublicRentalCreateResponse from(RentalMock rental) {
    return new PublicRentalCreateResponse(
        rental.id(),
        rental.itemId(),
        rental.status().name(),
        "대여 요청이 완료되었습니다."
    );
  }
}
