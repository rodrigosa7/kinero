package dev.kinero.kinerobackend.training.model;
import dev.kinero.kinerobackend.common.model.BaseEntity;
import dev.kinero.kinerobackend.user.model.User;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workout extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "routine_id")
    private java.util.UUID routineId;
    @Column(nullable = false)
    private String name; // e.g., "Push Day", "Chest & Triceps"
    @Column(length = 500)
    private String notes;
    @Column(name = "started_at", nullable = false)
    private LocalDateTime startTime;
    @Column(name = "finished_at")
    private LocalDateTime endTime;
    @Column(name = "total_volume")
    private java.math.BigDecimal totalVolume;
    @Column(nullable = false)
    private LocalDate workoutDate;

    @OneToMany(mappedBy = "workout", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<WorkoutExercise> exercises = new HashSet<>();

    @Column(nullable = false)
    @Builder.Default
    private Integer totalSets = 0;

    @Column(nullable = false)
    @Builder.Default
    private Boolean completed = false;

    @Column(nullable = false)
    @Builder.Default
    private Integer durationMinutes = 0;
}
