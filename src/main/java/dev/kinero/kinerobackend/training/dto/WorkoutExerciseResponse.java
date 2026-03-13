package dev.kinero.kinerobackend.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutExerciseResponse {
    private UUID id;
    private ExerciseResponse exercise;
    private Integer position;
    private Integer plannedSets;
    private Integer plannedReps;
    private String notes;
    private Boolean completed;
    private Set<ExerciseSetResponse> sets;
}
