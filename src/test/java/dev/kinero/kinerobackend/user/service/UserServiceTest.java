package dev.kinero.kinerobackend.user.service;

import dev.kinero.kinerobackend.user.model.User;
import dev.kinero.kinerobackend.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@Testcontainers
class UserServiceTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.flyway.enabled", () -> "false");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void register_newUser_success() {
        // Given
        String email = "potato@example.com";

        // When
        User user = userService.register(email, "password123");

        // Then
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isNotEqualTo("password123"); // hashed

        // Verify persisted in PostgreSQL
        User savedUser = userRepository.findUserByEmail(email).orElse(null);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    void register_duplicateEmail_throwsException() {
        // Given
        String email = "duplicate@example.com";
        userService.register(email, "password123");

        // When & Then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.register(email, "different-password")
        );

        assertThat(exception.getMessage()).isEqualTo("Email already in use");

        User savedUser = userRepository.findUserByEmail(email).orElse(null);
        assertThat(savedUser).isNotNull();
    }

}
