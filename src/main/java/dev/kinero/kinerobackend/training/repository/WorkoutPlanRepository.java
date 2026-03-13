package dev.kinero.kinerobackend.training.repository;
import dev.kinero.kinerobackend.training.model.WorkoutPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, UUID> {
    Page<WorkoutPlan> findByUserIdAndDeletedFalse(UUID userId, Pageable pageable);

    @Query("SELECT wp FROM WorkoutPlan wp WHERE wp.user.id = :userId AND wp.active = true AND wp.deleted = false")
    Optional<WorkoutPlan> findActiveByUserId(UUID userId);

    @Query("SELECT wp FROM WorkoutPlan wp WHERE wp.user.id = :userId AND wp.deleted = false ORDER BY wp.startDate DESC")
    List<WorkoutPlan> findByUserIdOrderByStartDate(UUID userId);

    Optional<WorkoutPlan> findByIdAndDeletedFalse(UUID id);
}
