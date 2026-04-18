package retrivr.retrivrspring.presentation.admin.rental.res;

import retrivr.retrivrspring.mock.model.RentalMock;

public record AdminRentalDecisionResponse(
    Long rentalId,
    String status,
    String message,
    String processedBy
) {

  public static AdminRentalDecisionResponse approved(RentalMock rental, String adminName) {
    return new AdminRentalDecisionResponse(
        rental.id(),
        rental.status().name(),
        "대여가 승인되었습니다.",
        adminName
    );
  }

  public static AdminRentalDecisionResponse rejected(RentalMock rental, String adminName) {
    return new AdminRentalDecisionResponse(
        rental.id(),
        rental.status().name(),
        "대여 요청이 거절되었습니다.",
        adminName
    );
  }
}
