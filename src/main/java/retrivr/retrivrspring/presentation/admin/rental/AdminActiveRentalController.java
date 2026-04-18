package retrivr.retrivrspring.presentation.admin.rental;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrivr.retrivrspring.application.service.admin.rental.AdminActiveRentalService;
import retrivr.retrivrspring.presentation.admin.rental.req.AdminRentalReturnRequest;
import retrivr.retrivrspring.presentation.admin.rental.res.AdminOverdueRentalItemPageResponse;
import retrivr.retrivrspring.presentation.admin.rental.res.AdminRentalReturnResponse;

@RestController
@RequestMapping("/api/admin/v1")
public class AdminActiveRentalController {

  private final AdminActiveRentalService adminActiveRentalService;

  public AdminActiveRentalController(AdminActiveRentalService adminActiveRentalService) {
    this.adminActiveRentalService = adminActiveRentalService;
  }

  @GetMapping("/rentals/overdue")
  public AdminOverdueRentalItemPageResponse getOverdueItemList() {
    return adminActiveRentalService.getOverdueItemList();
  }

  @PostMapping("/rentals/{rentalId}/return")
  public AdminRentalReturnResponse confirmReturn(
      @PathVariable Long rentalId,
      @Valid @RequestBody AdminRentalReturnRequest request
  ) {
    return adminActiveRentalService.confirmReturn(rentalId, request);
  }
}
