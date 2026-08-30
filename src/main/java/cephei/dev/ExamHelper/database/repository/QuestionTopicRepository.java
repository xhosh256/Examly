package cephei.dev.ExamHelper.database.repository;

import cephei.dev.ExamHelper.database.entity.QuestionTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTopicRepository extends JpaRepository<QuestionTopic, Long> {
}
