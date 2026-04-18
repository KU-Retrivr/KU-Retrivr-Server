package retrivr.retrivrspring.presentation.open.rental.req;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record PublicRentalCreateRequest(
    @NotBlank(message = "Borrower name is required.")
    String borrowerName,

    @NotBlank(message = "Borrower phone is required.")
    String borrowerPhone,

    @NotBlank(message = "Organization is required.")
    String organization,

    @NotBlank(message = "Purpose is required.")
    String purpose,

    @NotNull(message = "Rental date is required.")
    @FutureOrPresent(message = "Rental date must be today or later.")
    LocalDate rentalDate,

    @NotNull(message = "Return date is required.")
    @FutureOrPresent(message = "Return date must be today or later.")
    LocalDate returnDate
) {
}
