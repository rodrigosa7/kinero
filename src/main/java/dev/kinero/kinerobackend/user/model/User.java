package dev.kinero.kinerobackend.user.model;

import dev.kinero.kinerobackend.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    private String email;
    private String password;

    private Double weight;
    private Double height;

    @Column(name = "birth_date")
    private LocalDate dateOfBirth;
    private Gender gender;

    private UnitPreference preferredUnits;
}
