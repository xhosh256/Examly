package cephei.dev.ExamHelper.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "profiles")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class Profile extends BaseEntity<Integer> {

    @Column(nullable = false)
    private String firstname;

    private String lastname;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
