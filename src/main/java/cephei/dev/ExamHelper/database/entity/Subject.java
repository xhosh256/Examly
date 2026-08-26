package cephei.dev.ExamHelper.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "subjects")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Subject extends BaseEntity<Integer> {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private SubjectName subjectName;

    @OneToMany(
            mappedBy = "subject",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<TaskType> taskTypes = new ArrayList<>();
}
