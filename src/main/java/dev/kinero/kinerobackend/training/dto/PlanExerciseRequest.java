package dev.kinero.kinerobackend.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanExerciseRequest {
    private String exerciseId;
    private Integer position;
    private Integer sets;
    private Integer reps;
    private String notes;
}
