package cephei.dev.ExamHelper.database.repository;

import cephei.dev.ExamHelper.database.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {
    Page<Question> findAllByTaskType_Id(Long typeId, Pageable pageable);
}
