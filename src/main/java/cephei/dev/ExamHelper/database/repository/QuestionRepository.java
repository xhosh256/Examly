package cephei.dev.ExamHelper.database.repository;

import cephei.dev.ExamHelper.database.dto.QuestionReadDto;
import cephei.dev.ExamHelper.database.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<QuestionReadDto> findAllByTaskType_Id(Long typeId, Pageable pageable);
}
