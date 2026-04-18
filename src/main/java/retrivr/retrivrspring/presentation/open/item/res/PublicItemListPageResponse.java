package retrivr.retrivrspring.presentation.open.item.res;

import java.util.List;
import retrivr.retrivrspring.mock.model.ItemMock;
import retrivr.retrivrspring.mock.model.OrganizationMock;

public record PublicItemListPageResponse(
    Long organizationId,
    String organizationName,
    List<PublicItemSummary> items
) {

  public static PublicItemListPageResponse from(OrganizationMock organization, List<ItemMock> items) {
    return new PublicItemListPageResponse(
        organization.id(),
        organization.name(),
        items.stream().map(PublicItemSummary::from).toList()
    );
  }
}
