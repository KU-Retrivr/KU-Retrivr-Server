package retrivr.retrivrspring.application.service.open;

import org.springframework.stereotype.Service;
import retrivr.retrivrspring.mock.model.ItemMock;
import retrivr.retrivrspring.mock.store.MockDataStore;
import retrivr.retrivrspring.presentation.open.item.res.PublicItemDetailResponse;
import retrivr.retrivrspring.presentation.open.item.res.PublicItemListPageResponse;

@Service
public class PublicItemLookupService {

  private final MockDataStore mockDataStore;

  public PublicItemLookupService(MockDataStore mockDataStore) {
    this.mockDataStore = mockDataStore;
  }

  public PublicItemListPageResponse publicOrganizationItemListLookup(Long organizationId) {
    return PublicItemListPageResponse.from(
        mockDataStore.getOrganization(organizationId),
        mockDataStore.getItemsByOrganization(organizationId)
    );
  }

  public PublicItemDetailResponse publicOrganizationItemLookup(Long itemId) {
    ItemMock item = mockDataStore.getItem(itemId);
    return PublicItemDetailResponse.from(item);
  }
}
