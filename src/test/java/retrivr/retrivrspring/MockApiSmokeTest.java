package retrivr.retrivrspring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MockApiSmokeTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void publicOrganizationSearchWorks() throws Exception {
    mockMvc.perform(get("/api/public/v1/organizations/search").param("keyword", "학생회"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.organizations[0].name").value("컴퓨터공학과 학생회"));
  }

  @Test
  void adminLoginAndProtectedEndpointWork() throws Exception {
    mockMvc.perform(post("/api/admin/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                  "email": "admin@retrivr.com",
                  "password": "1234"
                }
                """))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.accessToken").value("mock-access-token"));

    mockMvc.perform(get("/api/admin/v1/items")
            .header("Authorization", "Bearer mock-access-token"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.items").isArray());
  }
}
