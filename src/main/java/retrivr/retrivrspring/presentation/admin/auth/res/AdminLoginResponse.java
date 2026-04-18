package retrivr.retrivrspring.presentation.admin.auth.res;

public record AdminLoginResponse(
    Long organizationId,
    String email,
    String accessToken,
    String refreshToken
) {
}
