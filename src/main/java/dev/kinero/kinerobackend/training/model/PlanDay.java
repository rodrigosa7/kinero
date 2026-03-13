package dev.kinero.kinerobackend.training.model;

import dev.kinero.kinerobackend.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plan_days")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDay extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_plan_id", nullable = false)
    private WorkoutPlan workoutPlan;

    @Column(nullable = false)
    private String dayName; // e.g., "Monday", "Push Day"

    @Column(nullable = false)
    private Integer dayOfWeek; // 1-7 (Monday-Sunday)

    @Column(length = 500)
    private String focusArea; // e.g., "Chest & Triceps"

    @OneToMany(mappedBy = "planDay", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<PlanExercise> exercises = new HashSet<>();
}
