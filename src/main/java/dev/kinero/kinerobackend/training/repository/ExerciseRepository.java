package dev.kinero.kinerobackend.training.repository;
import dev.kinero.kinerobackend.training.model.Exercise;
import dev.kinero.kinerobackend.training.model.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
    Optional<Exercise> findByName(String name);

    List<Exercise> findByPrimaryMuscle(MuscleGroup muscleGroup);

    List<Exercise> findByCompound(Boolean compound);

    List<Exercise> findByType(String type);

    @Query("SELECT e FROM Exercise e WHERE e.deleted = false ORDER BY e.name")
    List<Exercise> findAllActive();
}
