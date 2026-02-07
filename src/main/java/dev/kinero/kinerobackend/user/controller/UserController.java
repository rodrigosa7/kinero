package dev.kinero.kinerobackend.user.controller;

import dev.kinero.kinerobackend.user.dto.UserResponse;
import dev.kinero.kinerobackend.user.model.User;
import dev.kinero.kinerobackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
