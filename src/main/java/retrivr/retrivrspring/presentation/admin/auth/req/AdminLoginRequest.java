package retrivr.retrivrspring.presentation.admin.auth.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdminLoginRequest(
    @Email(message = "Valid email is required.")
    @NotBlank(message = "Email is required.")
    String email,

    @NotBlank(message = "Password is required.")
    String password
) {
}
