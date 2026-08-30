package cephei.dev.ExamHelper.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "task_types")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskType extends BaseEntity<Long> {

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "answer_type")
    private AnswerType answerType;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(
            mappedBy = "taskType",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question question) {
        questions.add(question);
        question.setTaskType(this);
    }
}
