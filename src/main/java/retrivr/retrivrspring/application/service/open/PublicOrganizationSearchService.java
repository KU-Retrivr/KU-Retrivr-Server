package retrivr.retrivrspring.application.service.open;

import org.springframework.stereotype.Service;
import retrivr.retrivrspring.mock.store.MockDataStore;
import retrivr.retrivrspring.presentation.open.organization.res.OrganizationSearchPageResponse;

@Service
public class PublicOrganizationSearchService {

  private final MockDataStore mockDataStore;

  public PublicOrganizationSearchService(MockDataStore mockDataStore) {
    this.mockDataStore = mockDataStore;
  }

  public OrganizationSearchPageResponse searchRankedPageByKeyword(String keyword) {
    return OrganizationSearchPageResponse.from(mockDataStore.searchOrganizations(keyword));
  }
}
