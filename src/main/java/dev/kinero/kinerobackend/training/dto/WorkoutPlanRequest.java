package dev.kinero.kinerobackend.training.dto;

import dev.kinero.kinerobackend.training.model.ProgramType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutPlanRequest {
    private String name;
    private String description;
    private ProgramType programType;
    private Integer daysPerWeek;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<PlanDayRequest> planDays;
}
