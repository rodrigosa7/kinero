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
public class ExerciseSetResponse {
    private UUID id;
    private Integer setNumber;
    private Double weight;
    private Integer reps;
    private Double rpe;
    private Integer duration;
    private String setType;
    private String notes;
    private Boolean completed;
}
