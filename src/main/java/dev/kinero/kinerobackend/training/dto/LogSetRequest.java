package dev.kinero.kinerobackend.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogSetRequest {
    private Integer setNumber;
    private Double weight;
    private Integer reps;
    private Integer duration;
    private Double rpe;
    private String setType;
}
