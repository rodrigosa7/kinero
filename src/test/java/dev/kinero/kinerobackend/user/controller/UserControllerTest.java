package dev.kinero.kinerobackend.user.controller;

import dev.kinero.kinerobackend.user.dto.UserRegistrationRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("User Controller Tests")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;


    @Test
    @WithMockUser
    @DisplayName("Should return 201 Created with user response when registering with valid credentials")
    void register_validRequest_returnsCreatedWithUserResponse() throws Exception {
        // Given
        String email = "test@example.com";
        String password = "password123";
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

        UserRegistrationRequest request = new UserRegistrationRequest(email, password);

        // When & Then
        mockMvc.perform(post("/api/users/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/users/" + userId))
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.weight").value(70.0))
                .andExpect(jsonPath("$.height").value(175.0))
                .andExpect(jsonPath("$.dateOfBirth").value("1990-01-01"))
                .andExpect(jsonPath("$.gender").value("MALE"))
                .andExpect(jsonPath("$.preferredUnits").value("METRIC"));
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
        UserRegistrationRequest request = new UserRegistrationRequest(email, password);

        // When & Then
        ResultActions resultActions = mockMvc.perform(post("/api/users/register")
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
    @DisplayName("Should return 500 Internal Server Error when registering with duplicate email")
    void register_duplicateEmail_returnsInternalServerError() throws Exception {
        // Given
        String email = "duplicate@example.com";
        UserRegistrationRequest request = new UserRegistrationRequest(email, "password123");

        when(userService.register(eq(email), any())).thenThrow(new IllegalArgumentException("Email already in use"));

        // When & Then
        mockMvc.perform(post("/api/users/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Email already in use"));
    }

    @Test
    @WithMockUser
    @DisplayName("Should return 200 OK with user data when getting existing user by ID")
    void getUserById_existingUser_returnsUser() throws Exception {
        // Given
        UUID userId = UUID.randomUUID();
        User user = User.builder()
                .email("test@example.com")
                .password("hashedPassword")
                .weight(65.5)
                .height(170.0)
                .dateOfBirth(LocalDate.of(1995, 6, 15))
                .gender(Gender.FEMALE)
                .preferredUnits(UnitPreference.IMPERIAL)
                .build();
        user.setId(userId);

        when(userService.findById(userId)).thenReturn(user);

        // When & Then
        mockMvc.perform(get("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.weight").value(65.5))
                .andExpect(jsonPath("$.height").value(170.0))
                .andExpect(jsonPath("$.dateOfBirth").value("1995-06-15"))
                .andExpect(jsonPath("$.gender").value("FEMALE"))
                .andExpect(jsonPath("$.preferredUnits").value("IMPERIAL"));
    }

    @Test
    @WithMockUser
    @DisplayName("Should return 500 Internal Server Error when getting non-existing user by ID")
    void getUserById_nonExistingUser_returnsInternalServerError() throws Exception {
        // Given
        UUID userId = UUID.randomUUID();

        when(userService.findById(userId)).thenThrow(new RuntimeException("User not found"));

        // When & Then
        mockMvc.perform(get("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("User not found"));
    }
}
