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
public class PlanDayResponse {
    private UUID id;
    private String dayName;
    private Integer dayOfWeek;
    private String focusArea;
    private Set<PlanExerciseResponse> exercises;
}
