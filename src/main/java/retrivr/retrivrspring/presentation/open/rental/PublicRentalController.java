package retrivr.retrivrspring.presentation.open.rental;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrivr.retrivrspring.application.service.open.PublicRentalService;
import retrivr.retrivrspring.presentation.open.rental.req.PublicRentalCreateRequest;
import retrivr.retrivrspring.presentation.open.rental.res.PublicRentalCreateResponse;
import retrivr.retrivrspring.presentation.open.rental.res.PublicRentalDetailResponse;

@RestController
@RequestMapping("/api/public/v1")
public class PublicRentalController {

  private final PublicRentalService publicRentalService;

  public PublicRentalController(PublicRentalService publicRentalService) {
    this.publicRentalService = publicRentalService;
  }

  @PostMapping("/items/{itemId}/rentals")
  public PublicRentalCreateResponse createRental(
      @PathVariable Long itemId,
      @Valid @RequestBody PublicRentalCreateRequest request
  ) {
    return publicRentalService.requestRental(itemId, request);
  }

  @GetMapping("/rentals/{rentalId}")
  public PublicRentalDetailResponse getRentalInfo(@PathVariable Long rentalId) {
    return publicRentalService.checkRentalStatusAndDetail(rentalId);
  }
}
