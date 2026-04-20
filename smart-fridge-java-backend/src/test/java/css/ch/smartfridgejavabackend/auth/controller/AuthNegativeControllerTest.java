package css.ch.smartfridgejavabackend.auth.controller;

import css.ch.smartfridgejavabackend.auth.controller.AuthController;
import css.ch.smartfridgejavabackend.auth.service.AuthService;
import css.ch.smartfridgejavabackend.auth.service.JwtService;
import css.ch.smartfridgejavabackend.user.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthNegativeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtService jwtService;

    @Test
    void shouldReturnBadRequestWhenLoginPayloadIsInvalid() throws Exception {
        String invalidBody = """
                {
                  "email": "invalid-mail",
                  "password": "stricher"
                }
                """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidBody))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(authService, jwtService);
    }
}
