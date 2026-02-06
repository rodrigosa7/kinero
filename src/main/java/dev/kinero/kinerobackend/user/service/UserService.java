package dev.kinero.kinerobackend.user.service;

import dev.kinero.kinerobackend.user.model.User;

import java.util.UUID;

public interface UserService {
    User register(String email, String rawPassword);
    User findByEmail(String email);
    User findById(UUID id);

}
