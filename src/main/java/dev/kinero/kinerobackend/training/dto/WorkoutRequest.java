package dev.kinero.kinerobackend.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutRequest {
    private String name;
    private String notes;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDate workoutDate;
    private UUID routineId;
    private Set<WorkoutExerciseRequest> exercises;
}
