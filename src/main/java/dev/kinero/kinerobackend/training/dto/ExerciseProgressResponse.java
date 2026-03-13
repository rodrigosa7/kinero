package dev.kinero.kinerobackend.training.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseProgressResponse {
    private UUID exerciseId;
    private Double maxWeight;
    private Integer maxReps;
    private Double highestVolume;
    private List<HistoryEntry> history;

    @Data
    @AllArgsConstructor
    public static class HistoryEntry {
        private LocalDate date;
        private Double maxWeight;
    }
}
