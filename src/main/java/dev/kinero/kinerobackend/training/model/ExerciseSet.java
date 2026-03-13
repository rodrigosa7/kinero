package dev.kinero.kinerobackend.training.model;
import dev.kinero.kinerobackend.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "sets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseSet extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_exercise_id", nullable = false)
    private WorkoutExercise workoutExercise;
    @Column(name = "set_number", nullable = false)
    private Integer setNumber;
    @Column(nullable = false)
    private Double weight; // in kg or lbs depending on user preference
    @Column(nullable = false)
    private Integer reps;
    private Integer duration;
    private Double rpe; // Rate of Perceived Exertion (1-10)
    @Column(name = "set_type", length = 20)
    private String setType;
    @Column(length = 500)
    private String notes;
    @Column(nullable = false)
    private Boolean completed = false;
}
