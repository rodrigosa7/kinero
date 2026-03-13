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
public class PlanDayRequest {
    private String dayName;
    private Integer dayOfWeek;
    private String focusArea;
    private Set<PlanExerciseRequest> exercises;
}
