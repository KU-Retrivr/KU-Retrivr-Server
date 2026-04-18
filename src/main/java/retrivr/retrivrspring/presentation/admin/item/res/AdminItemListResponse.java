package retrivr.retrivrspring.presentation.admin.item.res;

import java.util.List;
import retrivr.retrivrspring.mock.model.ItemMock;

public record AdminItemListResponse(
    List<AdminItemSummary> items
) {

  public static AdminItemListResponse from(List<ItemMock> items) {
    return new AdminItemListResponse(items.stream().map(AdminItemSummary::from).toList());
  }

  public record AdminItemSummary(
      Long id,
      String name,
      Long organizationId,
      int totalQuantity,
      int availableQuantity
  ) {
    public static AdminItemSummary from(ItemMock item) {
      return new AdminItemSummary(
          item.id(),
          item.name(),
          item.organizationId(),
          item.totalQuantity(),
          item.availableQuantity()
      );
    }
  }
}
