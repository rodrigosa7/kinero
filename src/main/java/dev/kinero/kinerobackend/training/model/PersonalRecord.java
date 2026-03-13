package dev.kinero.kinerobackend.training.model;
import dev.kinero.kinerobackend.common.model.BaseEntity;
import dev.kinero.kinerobackend.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@Entity
@Table(
    name = "personal_records",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "exercise_id", "rep_range"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalRecord extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(nullable = false)
    private Double weight; // in kg or lbs

    @Column(nullable = false)
    private Integer repRange; // 1 rep max, 5 rep max, 10 rep max, etc.

    @Column(nullable = false)
    private LocalDate achievedDate;

    @Column(length = 500)
    private String notes;
}
