package retrivr.retrivrspring.presentation.admin.rental;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrivr.retrivrspring.application.service.admin.rental.AdminRequestedRentalService;
import retrivr.retrivrspring.presentation.admin.rental.req.AdminRentalApproveRequest;
import retrivr.retrivrspring.presentation.admin.rental.req.AdminRentalRejectRequest;
import retrivr.retrivrspring.presentation.admin.rental.res.AdminRentalDecisionResponse;
import retrivr.retrivrspring.presentation.admin.rental.res.AdminRentalRequestPageResponse;

@RestController
@RequestMapping("/api/admin/v1/rentals")
public class AdminRequestedRentalController {

  private final AdminRequestedRentalService adminRequestedRentalService;

  public AdminRequestedRentalController(AdminRequestedRentalService adminRequestedRentalService) {
    this.adminRequestedRentalService = adminRequestedRentalService;
  }

  @GetMapping("/requests")
  public AdminRentalRequestPageResponse getRequestedList() {
    return adminRequestedRentalService.getRequestedList();
  }

  @PostMapping("/{rentalId}/approve")
  public AdminRentalDecisionResponse approve(
      @PathVariable Long rentalId,
      @Valid @RequestBody AdminRentalApproveRequest request
  ) {
    return adminRequestedRentalService.approveRentalRequest(rentalId, request);
  }

  @PostMapping("/{rentalId}/reject")
  public AdminRentalDecisionResponse reject(
      @PathVariable Long rentalId,
      @Valid @RequestBody AdminRentalRejectRequest request
  ) {
    return adminRequestedRentalService.rejectRentalRequest(rentalId, request);
  }
}
