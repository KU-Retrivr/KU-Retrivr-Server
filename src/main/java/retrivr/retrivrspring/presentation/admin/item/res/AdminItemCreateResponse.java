package retrivr.retrivrspring.presentation.admin.item.res;

import retrivr.retrivrspring.mock.model.ItemMock;

public record AdminItemCreateResponse(
    Long id,
    String name,
    String description,
    Long organizationId,
    int totalQuantity,
    int availableQuantity
) {

  public static AdminItemCreateResponse from(ItemMock item) {
    return new AdminItemCreateResponse(
        item.id(),
        item.name(),
        item.description(),
        item.organizationId(),
        item.totalQuantity(),
        item.availableQuantity()
    );
  }
}
