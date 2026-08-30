package cephei.dev.ExamHelper.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "question_topics")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionTopic extends BaseEntity<Long> {

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "question_id")
    private Question question;

    public void setQuestion(Question question) {
        this.question = question;
        question.getQuestionTopics().add(this);
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
        topic.getQuestionTopics().add(this);
    }
}