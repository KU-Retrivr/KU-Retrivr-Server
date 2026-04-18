package retrivr.retrivrspring.application.service.open;

import org.springframework.stereotype.Service;
import retrivr.retrivrspring.mock.model.RentalMock;
import retrivr.retrivrspring.mock.store.MockDataStore;
import retrivr.retrivrspring.presentation.open.rental.req.PublicRentalCreateRequest;
import retrivr.retrivrspring.presentation.open.rental.res.PublicRentalCreateResponse;
import retrivr.retrivrspring.presentation.open.rental.res.PublicRentalDetailResponse;

@Service
public class PublicRentalService {

  private final MockDataStore mockDataStore;

  public PublicRentalService(MockDataStore mockDataStore) {
    this.mockDataStore = mockDataStore;
  }

  public PublicRentalCreateResponse requestRental(Long itemId, PublicRentalCreateRequest request) {
    RentalMock rental = mockDataStore.createRental(
        itemId,
        request.borrowerName(),
        request.borrowerPhone(),
        request.organization(),
        request.purpose(),
        request.rentalDate(),
        request.returnDate()
    );
    return PublicRentalCreateResponse.from(rental);
  }

  public PublicRentalDetailResponse checkRentalStatusAndDetail(Long rentalId) {
    return PublicRentalDetailResponse.from(mockDataStore.getRental(rentalId));
  }
}
