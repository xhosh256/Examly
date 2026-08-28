package cephei.dev.ExamHelper.database.repository;

import cephei.dev.ExamHelper.database.entity.Question;
import cephei.dev.ExamHelper.database.entity.SubjectName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findAllByTaskType_Id(Long typeId, Pageable pageable);
    Page<Question> findAllByTaskType_Subject_subjectNameAndTaskType_Number(SubjectName subjectName, Integer number, Pageable pageable);
}
