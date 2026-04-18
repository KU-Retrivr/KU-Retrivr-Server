package retrivr.retrivrspring.presentation.open.organization;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import retrivr.retrivrspring.application.service.open.PublicOrganizationSearchService;
import retrivr.retrivrspring.presentation.open.organization.res.OrganizationSearchPageResponse;

@RestController
@RequestMapping("/api/public/v1/organizations/search")
public class PublicOrganizationSearchController {

  private final PublicOrganizationSearchService publicOrganizationSearchService;

  public PublicOrganizationSearchController(PublicOrganizationSearchService publicOrganizationSearchService) {
    this.publicOrganizationSearchService = publicOrganizationSearchService;
  }

  @GetMapping
  public OrganizationSearchPageResponse searchRankedPageByKeyword(
      @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword
  ) {
    return publicOrganizationSearchService.searchRankedPageByKeyword(keyword);
  }
}
