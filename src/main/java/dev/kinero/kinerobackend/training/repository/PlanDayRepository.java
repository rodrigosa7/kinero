package dev.kinero.kinerobackend.training.repository;
import dev.kinero.kinerobackend.training.model.PlanDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
@Repository
public interface PlanDayRepository extends JpaRepository<PlanDay, UUID> {
    List<PlanDay> findByWorkoutPlanId(UUID workoutPlanId);

    void deleteByWorkoutPlanId(UUID workoutPlanId);
}
