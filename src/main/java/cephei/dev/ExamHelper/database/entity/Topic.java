package cephei.dev.ExamHelper.database.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "topics")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Topic extends BaseEntity<Long> {

    @Column(nullable = false)
    private String topic;

    @Builder.Default
    @OneToMany(
            mappedBy = "topic",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<QuestionTopic> questionTopics = new ArrayList<>();
}
