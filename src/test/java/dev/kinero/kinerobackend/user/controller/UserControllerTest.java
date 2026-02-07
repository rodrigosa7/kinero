package dev.kinero.kinerobackend.user.controller;

import dev.kinero.kinerobackend.common.error.ResourceNotFoundException;
import dev.kinero.kinerobackend.user.model.Gender;
import dev.kinero.kinerobackend.user.model.UnitPreference;
import dev.kinero.kinerobackend.user.model.User;
import dev.kinero.kinerobackend.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("User Controller Tests")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;


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
    @DisplayName("Should return 404 Not Found when getting non-existing user by ID")
    void getUserById_nonExistingUser_returnsNotFound() throws Exception {
        // Given
        UUID userId = UUID.randomUUID();

        when(userService.findById(userId)).thenThrow(new ResourceNotFoundException("User", "id", userId));

        // When & Then
        mockMvc.perform(get("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not found with id: '" + userId + "'"));
    }
}
