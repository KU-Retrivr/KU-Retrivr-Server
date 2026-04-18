package retrivr.retrivrspring.presentation.open.item;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrivr.retrivrspring.application.service.open.PublicItemLookupService;
import retrivr.retrivrspring.presentation.open.item.res.PublicItemDetailResponse;
import retrivr.retrivrspring.presentation.open.item.res.PublicItemListPageResponse;

@RestController
@RequestMapping("/api/public/v1")
public class PublicItemLookupController {

  private final PublicItemLookupService publicItemLookupService;

  public PublicItemLookupController(PublicItemLookupService publicItemLookupService) {
    this.publicItemLookupService = publicItemLookupService;
  }

  @GetMapping("/organizations/{organizationId}/items")
  public PublicItemListPageResponse getOrgItems(@PathVariable Long organizationId) {
    return publicItemLookupService.publicOrganizationItemListLookup(organizationId);
  }

  @GetMapping("/items/{itemId}")
  public PublicItemDetailResponse getOrgItemDetail(@PathVariable Long itemId) {
    return publicItemLookupService.publicOrganizationItemLookup(itemId);
  }
}
