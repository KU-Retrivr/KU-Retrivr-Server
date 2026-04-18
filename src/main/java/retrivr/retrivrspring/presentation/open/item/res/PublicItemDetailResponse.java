package retrivr.retrivrspring.presentation.open.item.res;

import retrivr.retrivrspring.mock.model.ItemMock;

public record PublicItemDetailResponse(
    Long id,
    String name,
    String description,
    Long organizationId,
    String organizationName,
    int totalQuantity,
    int availableQuantity,
    String status
) {

  public static PublicItemDetailResponse from(ItemMock item) {
    return new PublicItemDetailResponse(
        item.id(),
        item.name(),
        item.description(),
        item.organizationId(),
        item.organizationName(),
        item.totalQuantity(),
        item.availableQuantity(),
        item.availableQuantity() > 0 ? "AVAILABLE" : "UNAVAILABLE"
    );
  }
}
