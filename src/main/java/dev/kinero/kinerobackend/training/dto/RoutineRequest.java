package dev.kinero.kinerobackend.training.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineRequest {
    @NotBlank
    private String name;
    private String description;
    private UUID userId;
}
