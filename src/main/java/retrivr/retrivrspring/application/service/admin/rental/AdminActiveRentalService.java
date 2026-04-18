package retrivr.retrivrspring.application.service.admin.rental;

import org.springframework.stereotype.Service;
import retrivr.retrivrspring.mock.store.MockDataStore;
import retrivr.retrivrspring.presentation.admin.rental.req.AdminRentalReturnRequest;
import retrivr.retrivrspring.presentation.admin.rental.res.AdminOverdueRentalItemPageResponse;
import retrivr.retrivrspring.presentation.admin.rental.res.AdminRentalReturnResponse;

@Service
public class AdminActiveRentalService {

  private final MockDataStore mockDataStore;

  public AdminActiveRentalService(MockDataStore mockDataStore) {
    this.mockDataStore = mockDataStore;
  }

  public AdminOverdueRentalItemPageResponse getOverdueItemList() {
    return AdminOverdueRentalItemPageResponse.from(mockDataStore.getOverdueRentals());
  }

  public AdminRentalReturnResponse confirmReturn(Long rentalId, AdminRentalReturnRequest request) {
    return AdminRentalReturnResponse.from(
        mockDataStore.returnRental(rentalId),
        request.adminNameToConfirm()
    );
  }
}
