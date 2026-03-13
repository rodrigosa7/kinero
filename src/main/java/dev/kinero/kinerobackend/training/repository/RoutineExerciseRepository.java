package dev.kinero.kinerobackend.training.repository;
import dev.kinero.kinerobackend.training.model.RoutineExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface RoutineExerciseRepository extends JpaRepository<RoutineExercise, UUID> {
}
