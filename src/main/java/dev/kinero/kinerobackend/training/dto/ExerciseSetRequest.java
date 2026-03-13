package dev.kinero.kinerobackend.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseSetRequest {
    private Integer setNumber;
    private Double weight;
    private Integer reps;
    private Integer rpe;
    private Integer duration;
    private String setType;
    private String notes;
}
