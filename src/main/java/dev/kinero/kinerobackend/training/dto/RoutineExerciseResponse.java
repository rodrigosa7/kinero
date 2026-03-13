package dev.kinero.kinerobackend.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineExerciseResponse {
    private UUID id;
    private UUID exerciseId;
    private String exerciseName;
    private Integer orderIndex;
    private Integer targetSets;
    private Integer targetReps;
    private Integer restSeconds;
}
