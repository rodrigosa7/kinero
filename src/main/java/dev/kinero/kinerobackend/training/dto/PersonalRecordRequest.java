package dev.kinero.kinerobackend.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalRecordRequest {
    private String exerciseId;
    private Double weight;
    private Integer repRange;
    private LocalDate achievedDate;
    private String notes;
}
