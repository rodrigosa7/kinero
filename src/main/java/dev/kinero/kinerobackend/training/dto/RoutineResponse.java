package dev.kinero.kinerobackend.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineResponse {
    private UUID id;
    private UUID userId;
    private String name;
    private String description;
    private Instant createdAt;
    private List<RoutineExerciseResponse> exercises;
}
