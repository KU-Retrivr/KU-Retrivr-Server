package retrivr.retrivrspring.presentation.admin.rental.req;

import jakarta.validation.constraints.NotBlank;

public record AdminRentalApproveRequest(
    @NotBlank(message = "Admin name is required.")
    String adminNameToApprove
) {
}
