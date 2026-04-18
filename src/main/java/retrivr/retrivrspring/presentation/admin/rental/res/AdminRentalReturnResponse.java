package retrivr.retrivrspring.presentation.admin.rental.res;

import retrivr.retrivrspring.mock.model.RentalMock;

public record AdminRentalReturnResponse(
    Long rentalId,
    String status,
    String message,
    String processedBy
) {

  public static AdminRentalReturnResponse from(RentalMock rental, String adminName) {
    return new AdminRentalReturnResponse(
        rental.id(),
        rental.status().name(),
        "반납이 완료되었습니다.",
        adminName
    );
  }
}
