package dev.kinero.kinerobackend.user.controller;

import dev.kinero.kinerobackend.user.dto.UserRegistrationRequest;
import dev.kinero.kinerobackend.user.dto.UserResponse;
import dev.kinero.kinerobackend.user.model.User;
import dev.kinero.kinerobackend.user.service.UserService;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MeterRegistry meterRegistry;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegistrationRequest request) {
        User user = userService.register(request.email(), request.password());

        UserResponse response = new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getWeight(),
                user.getHeight(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getPreferredUnits()
        );

        meterRegistry.counter("user.registration.count").increment();

        return ResponseEntity.created(URI.create("/api/users/" + user.getId())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        User user = userService.findById(java.util.UUID.fromString(id));

        UserResponse response = new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getWeight(),
                user.getHeight(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getPreferredUnits()
        );

        return ResponseEntity.ok(response);
    }
}
