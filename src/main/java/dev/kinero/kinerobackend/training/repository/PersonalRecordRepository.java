package dev.kinero.kinerobackend.training.repository;
import dev.kinero.kinerobackend.training.model.PersonalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface PersonalRecordRepository extends JpaRepository<PersonalRecord, UUID> {
    Page<PersonalRecord> findByUserIdAndDeletedFalse(UUID userId, Pageable pageable);

    List<PersonalRecord> findByUserIdAndExerciseIdAndDeletedFalse(UUID userId, UUID exerciseId);

    Optional<PersonalRecord> findByUserIdAndExerciseIdAndRepRangeAndDeletedFalse(UUID userId, UUID exerciseId, Integer repRange);

    @Query("SELECT pr FROM PersonalRecord pr WHERE pr.user.id = :userId AND pr.deleted = false ORDER BY pr.achievedDate DESC")
    List<PersonalRecord> findRecentPRs(UUID userId);
}
