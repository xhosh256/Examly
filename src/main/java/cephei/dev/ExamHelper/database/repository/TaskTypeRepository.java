package cephei.dev.ExamHelper.database.repository;

import cephei.dev.ExamHelper.database.entity.SubjectName;
import cephei.dev.ExamHelper.database.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {

    Optional<TaskType> findBySubject_SubjectNameAndNumber(SubjectName subjectName, Integer number);
}
