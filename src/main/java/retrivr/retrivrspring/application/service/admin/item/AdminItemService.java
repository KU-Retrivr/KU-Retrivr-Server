package retrivr.retrivrspring.application.service.admin.item;

import org.springframework.stereotype.Service;
import retrivr.retrivrspring.mock.store.MockDataStore;
import retrivr.retrivrspring.presentation.admin.item.req.AdminItemCreateRequest;
import retrivr.retrivrspring.presentation.admin.item.res.AdminItemCreateResponse;
import retrivr.retrivrspring.presentation.admin.item.res.AdminItemListResponse;

@Service
public class AdminItemService {

  private final MockDataStore mockDataStore;

  public AdminItemService(MockDataStore mockDataStore) {
    this.mockDataStore = mockDataStore;
  }

  public AdminItemCreateResponse createItem(AdminItemCreateRequest request) {
    return AdminItemCreateResponse.from(
        mockDataStore.createItem(
            request.organizationId(),
            request.name(),
            request.description(),
            request.totalQuantity()
        )
    );
  }

  public AdminItemListResponse getItems() {
    return AdminItemListResponse.from(mockDataStore.getAllItems());
  }
}
