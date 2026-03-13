package dev.kinero.kinerobackend.training.repository;
import dev.kinero.kinerobackend.training.model.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, UUID> {
    List<ExerciseSet> findByWorkoutExerciseId(UUID workoutExerciseId);

    void deleteByWorkoutExerciseId(UUID workoutExerciseId);
    @Query("SELECT MAX(s.weight) FROM ExerciseSet s JOIN s.workoutExercise we WHERE we.exercise.id = :exerciseId")
    Double findMaxWeightByExerciseId(@Param("exerciseId") UUID exerciseId);
    @Query("SELECT MAX(s.reps) FROM ExerciseSet s JOIN s.workoutExercise we WHERE we.exercise.id = :exerciseId")
    Integer findMaxRepsByExerciseId(@Param("exerciseId") UUID exerciseId);
    @Query("SELECT s FROM ExerciseSet s JOIN s.workoutExercise we WHERE we.exercise.id = :exerciseId AND (s.weight * s.reps) = (SELECT MAX(s2.weight * s2.reps) FROM ExerciseSet s2 JOIN s2.workoutExercise we2 WHERE we2.exercise.id = :exerciseId)")
    List<ExerciseSet> findHighestVolumeSetByExerciseId(@Param("exerciseId") UUID exerciseId);
    @Query("SELECT w.workoutDate as date, MAX(s.weight) as maxWeight FROM ExerciseSet s JOIN s.workoutExercise we JOIN we.workout w WHERE we.exercise.id = :exerciseId GROUP BY w.workoutDate ORDER BY w.workoutDate")
    List<Object[]> findWeightHistoryByExerciseId(@Param("exerciseId") UUID exerciseId);
}
