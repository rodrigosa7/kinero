package dev.kinero.kinerobackend.training.dto;

import dev.kinero.kinerobackend.training.model.ExerciseType;
import dev.kinero.kinerobackend.training.model.MuscleGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseRequest {
    @NotBlank
    private String name;
    private String category;
    private MuscleGroup primaryMuscle;
    private String equipment;
    private String instructions;
    private Boolean isCustom;

    private String description;
    private MuscleGroup secondaryMuscle;
    private ExerciseType type;
    private Boolean compound;
}
