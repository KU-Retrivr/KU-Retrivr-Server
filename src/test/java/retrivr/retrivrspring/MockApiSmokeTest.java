package retrivr.retrivrspring;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import retrivr.retrivrspring.application.service.admin.auth.AdminAuthService;
import retrivr.retrivrspring.application.service.admin.item.AdminItemService;
import retrivr.retrivrspring.application.service.open.PublicOrganizationSearchService;
import retrivr.retrivrspring.domain.entity.item.enumerate.ItemManagementType;
import retrivr.retrivrspring.global.auth.AuthUser;
import retrivr.retrivrspring.global.auth.AuthUserArgumentResolver;
import retrivr.retrivrspring.global.config.JwtTokenProvider;
import retrivr.retrivrspring.global.config.SecurityConfig;
import retrivr.retrivrspring.global.config.WebConfig;
import retrivr.retrivrspring.presentation.admin.auth.AdminAuthController;
import retrivr.retrivrspring.presentation.admin.auth.res.AdminLoginResponse;
import retrivr.retrivrspring.presentation.admin.item.AdminItemController;
import retrivr.retrivrspring.presentation.admin.item.res.AdminItemListResponse;
import retrivr.retrivrspring.presentation.admin.item.res.AdminItemPageResponse;
import retrivr.retrivrspring.presentation.open.organization.PublicOrganizationSearchController;
import retrivr.retrivrspring.presentation.open.organization.res.OrganizationSearchPageResponse;

@WebMvcTest({
    PublicOrganizationSearchController.class,
    AdminAuthController.class,
    AdminItemController.class
})
@AutoConfigureMockMvc
@Import({SecurityConfig.class, WebConfig.class, AuthUserArgumentResolver.class})
@ActiveProfiles("test")
class MockApiSmokeTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  JpaMetamodelMappingContext jpaMetamodelMappingContext;

  @MockitoBean
  private PublicOrganizationSearchService publicOrganizationSearchService;

  @MockitoBean
  private AdminAuthService adminAuthService;

  @MockitoBean
  private AdminItemService adminItemService;

  @MockitoBean
  private JwtTokenProvider jwtTokenProvider;

  @Test
  void publicOrganizationSearchWorks() throws Exception {
    given(publicOrganizationSearchService.searchRankedPageByKeyword("학생회", null, 15))
        .willReturn(new OrganizationSearchPageResponse(
            List.of(new OrganizationSearchPageResponse.OrganizationSearchSummary(
                1L,
                "컴퓨터공학과 학생회",
                null
            )),
            null
        ));

    mockMvc.perform(get("/api/public/v1/organizations/search").param("keyword", "학생회"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.organizations[0].name").value("컴퓨터공학과 학생회"));
  }

  @Test
  void adminLoginAndProtectedEndpointWork() throws Exception {
    given(adminAuthService.login(org.mockito.ArgumentMatchers.any()))
        .willReturn(new AdminLoginResponse(
            1L,
            "admin@retrivr.com",
            "mock-access-token",
            "mock-refresh-token"
        ));

    given(jwtTokenProvider.resolveToken("Bearer mock-access-token"))
        .willReturn("mock-access-token");
    given(jwtTokenProvider.validateToken("mock-access-token"))
        .willReturn(true);
    given(jwtTokenProvider.getAuthUser("mock-access-token"))
        .willReturn(new AuthUser(1L, "admin@retrivr.com"));

    given(adminItemService.getItems(1L, null, 15))
        .willReturn(new AdminItemPageResponse(
            List.of(new AdminItemListResponse(
                1L,
                "노트북",
                "대여용 노트북",
                7,
                3,
                2,
                ItemManagementType.NON_UNIT,
                false,
                null,
                List.of(),
                true
            )),
            null
        ));

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
