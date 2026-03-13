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
public class PlanExerciseResponse {
    private UUID id;
    private ExerciseResponse exercise;
    private Integer position;
    private Integer sets;
    private Integer reps;
    private String notes;
}
