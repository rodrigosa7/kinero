package dev.kinero.kinerobackend.training.model;
import dev.kinero.kinerobackend.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "exercises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(length = 50)
    private String category;
    @Enumerated(EnumType.STRING)
    @Column(name = "primary_muscle", nullable = false)
    private MuscleGroup primaryMuscle;
    @Enumerated(EnumType.STRING)
    private MuscleGroup secondaryMuscle;
    @Column(length = 100)
    private String equipment;
    @Column(columnDefinition = "TEXT")
    private String instructions;
    @Column(name = "is_custom")
    private boolean isCustom = false;
    @Column(name = "created_by_user_id")
    private java.util.UUID createdByUserId;
    @Column(length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExerciseType type; // Barbell, Dumbbell, Machine, Bodyweight, Cable
    @Column(nullable = false)
    private boolean compound; // true for compound lifts, false for isolation
}
