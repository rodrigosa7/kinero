package dev.kinero.kinerobackend.training.repository;
import dev.kinero.kinerobackend.training.model.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, UUID> {
    List<WorkoutExercise> findByWorkoutId(UUID workoutId);

    void deleteByWorkoutId(UUID workoutId);
}
