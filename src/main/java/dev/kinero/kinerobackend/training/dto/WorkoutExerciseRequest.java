package dev.kinero.kinerobackend.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutExerciseRequest {
    private String exerciseId;
    private Integer position;
    private Integer plannedSets;
    private Integer plannedReps;
    private String notes;
}
