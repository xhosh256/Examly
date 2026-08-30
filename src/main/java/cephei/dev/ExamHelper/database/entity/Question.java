package cephei.dev.ExamHelper.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "questions")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Question extends BaseEntity<Long> {

    @Column(nullable = false)
    private String question;

    @Column(name = "imageurl")
    private String imageUrl;

    @Column(nullable = false)
    private String answer;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "task_type_id")
    @EqualsAndHashCode.Exclude
    private TaskType taskType;

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private List<QuestionTopic> questionTopics = new ArrayList<>();

    @ManyToMany(mappedBy = "solvedQuestions", fetch = FetchType.LAZY)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private Set<User> solvedBy = new HashSet<>();
}
