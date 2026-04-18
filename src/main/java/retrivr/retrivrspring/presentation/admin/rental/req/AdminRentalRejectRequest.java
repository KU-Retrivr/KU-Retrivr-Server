package retrivr.retrivrspring.presentation.admin.rental.req;

import jakarta.validation.constraints.NotBlank;

public record AdminRentalRejectRequest(
    @NotBlank(message = "Admin name is required.")
    String adminNameToReject,

    @NotBlank(message = "Reject reason is required.")
    String reason
) {
}
