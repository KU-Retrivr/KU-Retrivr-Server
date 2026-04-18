package retrivr.retrivrspring.global.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import retrivr.retrivrspring.global.error.ApiException;

@Component
public class AdminAuthInterceptor implements HandlerInterceptor {

  public static final String MOCK_ACCESS_TOKEN = "mock-access-token";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    String header = request.getHeader("Authorization");
    if (!("Bearer " + MOCK_ACCESS_TOKEN).equals(header)) {
      throw new ApiException(HttpStatus.UNAUTHORIZED, "Admin authorization is required.");
    }
    return true;
  }
}
