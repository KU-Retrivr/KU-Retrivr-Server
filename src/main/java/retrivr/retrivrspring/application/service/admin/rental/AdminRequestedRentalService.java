package retrivr.retrivrspring.application.service.admin.rental;

import org.springframework.stereotype.Service;
import retrivr.retrivrspring.mock.store.MockDataStore;
import retrivr.retrivrspring.presentation.admin.rental.req.AdminRentalApproveRequest;
import retrivr.retrivrspring.presentation.admin.rental.req.AdminRentalRejectRequest;
import retrivr.retrivrspring.presentation.admin.rental.res.AdminRentalDecisionResponse;
import retrivr.retrivrspring.presentation.admin.rental.res.AdminRentalRequestPageResponse;

@Service
public class AdminRequestedRentalService {

  private final MockDataStore mockDataStore;

  public AdminRequestedRentalService(MockDataStore mockDataStore) {
    this.mockDataStore = mockDataStore;
  }

  public AdminRentalRequestPageResponse getRequestedList() {
    return AdminRentalRequestPageResponse.from(mockDataStore.getRequestedRentals());
  }

  public AdminRentalDecisionResponse approveRentalRequest(Long rentalId, AdminRentalApproveRequest request) {
    return AdminRentalDecisionResponse.approved(
        mockDataStore.approveRental(rentalId),
        request.adminNameToApprove()
    );
  }

  public AdminRentalDecisionResponse rejectRentalRequest(Long rentalId, AdminRentalRejectRequest request) {
    return AdminRentalDecisionResponse.rejected(
        mockDataStore.rejectRental(rentalId, request.reason()),
        request.adminNameToReject()
    );
  }
}
