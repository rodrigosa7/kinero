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
@Table(name = "workout_exercises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutExercise extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(nullable = false)
    private Integer position; // Order in workout

    @Column(nullable = false)
    @Builder.Default
    private Integer plannedSets = 3;

    @Column(nullable = false)
    @Builder.Default
    private Integer plannedReps = 8;

    @Column(length = 500)
    private String notes;

    @OneToMany(mappedBy = "workoutExercise", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ExerciseSet> sets = new HashSet<>();

    @Column(nullable = false)
    @Builder.Default
    private Boolean completed = false;
}
