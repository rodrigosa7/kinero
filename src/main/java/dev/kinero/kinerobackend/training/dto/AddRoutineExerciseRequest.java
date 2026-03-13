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
public class AddRoutineExerciseRequest {
    private UUID exerciseId;
    private Integer orderIndex;
    private Integer targetSets;
    private Integer targetReps;
    private Integer restSeconds;
}
