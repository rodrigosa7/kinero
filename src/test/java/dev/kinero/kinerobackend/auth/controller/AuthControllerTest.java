package dev.kinero.kinerobackend.auth.controller;

import dev.kinero.kinerobackend.auth.dto.RegisterRequest;
import dev.kinero.kinerobackend.auth.service.JwtService;
import dev.kinero.kinerobackend.common.error.ResourceAlreadyExistsException;
import dev.kinero.kinerobackend.user.model.Gender;
import dev.kinero.kinerobackend.user.model.UnitPreference;
import dev.kinero.kinerobackend.user.model.User;
import dev.kinero.kinerobackend.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Auth Controller Tests")
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtService jwtService;

    @Test
    @WithMockUser
    @DisplayName("Should return 201 Created with JWT token when registering with valid credentials")
    void register_validRequest_returnsCreatedWithToken() throws Exception {
        // Given
        String email = "test@example.com";
        String password = "password123";
        String token = "jwt.token.here";
        UUID userId = UUID.randomUUID();

        User user = User.builder()
                .email(email)
                .password("hashedPassword")
                .weight(70.0)
                .height(175.0)
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .gender(Gender.MALE)
                .preferredUnits(UnitPreference.METRIC)
                .build();
        user.setId(userId);

        when(userService.register(eq(email), eq(password))).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(token);

        RegisterRequest request = new RegisterRequest(email, password);

        // When & Then
        mockMvc.perform(post("/api/auth/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").value(token));
    }

    static Stream<Arguments> invalidRegistrationRequests() {
        return Stream.of(
                Arguments.of("invalid-email", "password123", "Must be a valid email address", "invalid email format"),
                Arguments.of("", "password123", null, "blank email"),
                Arguments.of("test@example.com", "", null, "blank password"),
                Arguments.of("test@example.com", "12345", "Password must be at least 6 characters long", "password too short")
        );
    }

    @ParameterizedTest(name = "Should return 400 Bad Request when registering with {3}")
    @MethodSource("invalidRegistrationRequests")
    @WithMockUser
    @SuppressWarnings("unused")
    void register_invalidInput_returnsBadRequest(String email, String password, String expectedMessage, String scenario) throws Exception {
        // Given
        RegisterRequest request = new RegisterRequest(email, password);

        // When & Then
        ResultActions resultActions = mockMvc.perform(post("/api/auth/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        if (expectedMessage != null) {
            resultActions.andExpect(jsonPath("$.message").value(expectedMessage));
        }
    }

    @Test
    @WithMockUser
    @DisplayName("Should return 409 Conflict when registering with duplicate email")
    void register_duplicateEmail_returnsConflict() throws Exception {
        // Given
        String email = "duplicate@example.com";
        RegisterRequest request = new RegisterRequest(email, "password123");

        when(userService.register(eq(email), any())).thenThrow(new ResourceAlreadyExistsException("User", "email", email));

        // When & Then
        mockMvc.perform(post("/api/auth/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("User already exists with email: '" + email + "'"));
    }
}
