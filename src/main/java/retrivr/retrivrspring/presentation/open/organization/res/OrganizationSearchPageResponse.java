package retrivr.retrivrspring.presentation.open.organization.res;

import java.util.List;
import retrivr.retrivrspring.mock.model.OrganizationMock;

public record OrganizationSearchPageResponse(
    List<OrganizationSummary> organizations
) {

  public static OrganizationSearchPageResponse from(List<OrganizationMock> organizations) {
    return new OrganizationSearchPageResponse(
        organizations.stream()
            .map(org -> new OrganizationSummary(org.id(), org.name()))
            .toList()
    );
  }

  public record OrganizationSummary(
      Long id,
      String name
  ) {
  }
}
