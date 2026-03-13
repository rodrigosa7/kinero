package dev.kinero.kinerobackend.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalRecordResponse {
    private UUID id;
    private ExerciseResponse exercise;
    private Double weight;
    private Integer repRange;
    private LocalDate achievedDate;
    private String notes;
}
