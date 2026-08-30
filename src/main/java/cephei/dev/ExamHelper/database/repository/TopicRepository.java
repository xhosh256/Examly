package cephei.dev.ExamHelper.database.repository;

import cephei.dev.ExamHelper.database.entity.SubjectName;
import cephei.dev.ExamHelper.database.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findAllByQuestionTopics_Question_TaskType_NumberAndQuestionTopics_Question_TaskType_Subject_SubjectName(Integer number, SubjectName subjectName);
}
