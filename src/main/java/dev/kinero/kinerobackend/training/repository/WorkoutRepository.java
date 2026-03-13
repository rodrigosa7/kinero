package dev.kinero.kinerobackend.training.repository;
import dev.kinero.kinerobackend.training.model.Workout;
import dev.kinero.kinerobackend.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, UUID> {
    Page<Workout> findByUserIdAndDeletedFalse(UUID userId, Pageable pageable);

    Page<Workout> findByUserIdAndWorkoutDateAndDeletedFalse(UUID userId, LocalDate workoutDate, Pageable pageable);

    List<Workout> findByUserIdAndWorkoutDateAndDeletedFalse(UUID userId, LocalDate workoutDate);

    @Query("SELECT w FROM Workout w WHERE w.user.id = :userId AND w.deleted = false ORDER BY w.workoutDate DESC")
    List<Workout> findRecentWorkouts(UUID userId);

    Optional<Workout> findByIdAndDeletedFalse(UUID id);
}
