package retrivr.retrivrspring.presentation.open.item.res;

import retrivr.retrivrspring.mock.model.ItemMock;

public record PublicItemSummary(
    Long id,
    String name,
    int availableQuantity,
    String status
) {

  public static PublicItemSummary from(ItemMock item) {
    return new PublicItemSummary(
        item.id(),
        item.name(),
        item.availableQuantity(),
        item.availableQuantity() > 0 ? "AVAILABLE" : "UNAVAILABLE"
    );
  }
}
