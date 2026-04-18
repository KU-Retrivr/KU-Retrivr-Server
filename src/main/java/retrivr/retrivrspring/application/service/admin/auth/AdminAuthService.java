package retrivr.retrivrspring.application.service.admin.auth;

import org.springframework.stereotype.Service;
import retrivr.retrivrspring.global.config.AdminAuthInterceptor;
import retrivr.retrivrspring.presentation.admin.auth.req.AdminLoginRequest;
import retrivr.retrivrspring.presentation.admin.auth.res.AdminLoginResponse;

@Service
public class AdminAuthService {

  public AdminLoginResponse login(AdminLoginRequest request) {
    return new AdminLoginResponse(
        1L,
        request.email(),
        AdminAuthInterceptor.MOCK_ACCESS_TOKEN,
        "mock-refresh-token"
    );
  }
}
