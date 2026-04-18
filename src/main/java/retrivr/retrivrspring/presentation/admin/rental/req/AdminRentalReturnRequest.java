package retrivr.retrivrspring.presentation.admin.rental.req;

import jakarta.validation.constraints.NotBlank;

public record AdminRentalReturnRequest(
    @NotBlank(message = "Admin name is required.")
    String adminNameToConfirm
) {
}
