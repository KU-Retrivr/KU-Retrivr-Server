package retrivr.retrivrspring.presentation.admin.item.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AdminItemCreateRequest(
    @NotBlank(message = "Item name is required.")
    String name,

    @NotBlank(message = "Item description is required.")
    String description,

    @NotNull(message = "Organization id is required.")
    Long organizationId,

    @NotNull(message = "Total quantity is required.")
    @Positive(message = "Total quantity must be positive.")
    Integer totalQuantity
) {
}
