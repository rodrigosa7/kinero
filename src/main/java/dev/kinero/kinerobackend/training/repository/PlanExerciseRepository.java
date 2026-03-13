package dev.kinero.kinerobackend.training.repository;
import dev.kinero.kinerobackend.training.model.PlanExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
@Repository
public interface PlanExerciseRepository extends JpaRepository<PlanExercise, UUID> {
    List<PlanExercise> findByPlanDayId(UUID planDayId);

    void deleteByPlanDayId(UUID planDayId);
}
