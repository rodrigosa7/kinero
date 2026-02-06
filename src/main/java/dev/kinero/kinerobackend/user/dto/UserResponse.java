package dev.kinero.kinerobackend.user.dto;

import dev.kinero.kinerobackend.user.model.Gender;
import dev.kinero.kinerobackend.user.model.UnitPreference;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        Double weight,
        Double height,
        LocalDate dateOfBirth,
        Gender gender,
        UnitPreference preferredUnits


) {}
