package retrivr.retrivrspring.presentation.admin.item;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrivr.retrivrspring.application.service.admin.item.AdminItemService;
import retrivr.retrivrspring.presentation.admin.item.req.AdminItemCreateRequest;
import retrivr.retrivrspring.presentation.admin.item.res.AdminItemCreateResponse;
import retrivr.retrivrspring.presentation.admin.item.res.AdminItemListResponse;

@RestController
@RequestMapping("/api/admin/v1/items")
public class AdminItemController {

  private final AdminItemService adminItemService;

  public AdminItemController(AdminItemService adminItemService) {
    this.adminItemService = adminItemService;
  }

  @PostMapping
  public AdminItemCreateResponse createItem(@Valid @RequestBody AdminItemCreateRequest request) {
    return adminItemService.createItem(request);
  }

  @GetMapping
  public AdminItemListResponse getItems() {
    return adminItemService.getItems();
  }
}
