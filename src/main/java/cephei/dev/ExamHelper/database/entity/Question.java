package cephei.dev.ExamHelper.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

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
    private TaskType taskType;

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @Builder.Default
    private List<QuestionTopic> questionTopics = new ArrayList<>();

}
