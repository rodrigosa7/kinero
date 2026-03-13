package dev.kinero.kinerobackend.training.dto;

import dev.kinero.kinerobackend.training.model.ExerciseType;
import dev.kinero.kinerobackend.training.model.MuscleGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseResponse {
    private UUID id;
    private String name;
    private String category;
    private MuscleGroup primaryMuscle;
    private String equipment;
    private String instructions;
    private Boolean isCustom;
    private UUID createdByUserId;

    private String description;
    private MuscleGroup secondaryMuscle;
    private ExerciseType type;
    private Boolean compound;
}
