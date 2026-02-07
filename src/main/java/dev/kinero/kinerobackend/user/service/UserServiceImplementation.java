package dev.kinero.kinerobackend.user.service;

import dev.kinero.kinerobackend.common.error.ResourceAlreadyExistsException;
import dev.kinero.kinerobackend.common.error.ResourceNotFoundException;
import dev.kinero.kinerobackend.user.model.User;
import dev.kinero.kinerobackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(String email, String rawPassword) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new ResourceAlreadyExistsException("User", "email", email);
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(rawPassword))
                .build();
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    public Integer calculateAge(User user) {
        if (user.getDateOfBirth() == null) {
            return null;
        }
        return Period.between(user.getDateOfBirth(), LocalDate.now()).getYears();
    }

}
