package cephei.dev.ExamHelper.database.specification;

import cephei.dev.ExamHelper.database.entity.*;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class QuestionSpecification {

    public static Specification<Question> hasTopicIds(Set<Long> topicIds) {
        return (root, query, criteriaBuilder) -> {

            if(topicIds == null || topicIds.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            Join<Question, QuestionTopic> questionTopic =
                    root.join("questionTopics");

            Join<QuestionTopic, Topic> topic =
                    questionTopic.join("topic");

            return topic.get("id").in(topicIds);
        };
    }

    public static Specification<Question> hasSubjectName(SubjectName subjectName) {
        return (root, query, criteriaBuilder) -> {

            if(subjectName == null) {
                return criteriaBuilder.conjunction();
            }

            Join<Question, TaskType> questionTaskType =
                    root.join("taskType");

            Join<TaskType, Subject> subject =
                    questionTaskType.join("subject");

            return criteriaBuilder.equal(
                    subject.get("subjectName"),
                    subjectName
            );
        };
    }


    public static Specification<Question> hasTypeNumber(Integer typeNumber) {
        return (root, query, criteriaBuilder) -> {

            if(typeNumber == null) {
                criteriaBuilder.conjunction();
            }

            Join<Question, TaskType> taskType =
                    root.join("taskType");


            return criteriaBuilder.equal(
                    taskType.get("number"),
                    typeNumber
            );
        };

    }
}
