package dev.kinero.kinerobackend.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutResponse {
    private UUID id;
    private String name;
    private String notes;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDate workoutDate;
    private Boolean completed;
    private Integer totalSets;
    private Integer durationMinutes;
    private BigDecimal totalVolume;
    private UUID routineId;
    private Set<WorkoutExerciseResponse> exercises;
}
