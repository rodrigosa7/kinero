package dev.kinero.kinerobackend.user.model;

import dev.kinero.kinerobackend.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

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
    private Integer age;
    private Gender gender;

    private UnitPreference preferredUnits;
}
