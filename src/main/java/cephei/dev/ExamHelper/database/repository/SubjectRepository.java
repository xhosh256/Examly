package cephei.dev.ExamHelper.database.repository;

import cephei.dev.ExamHelper.database.entity.Subject;
import cephei.dev.ExamHelper.database.entity.SubjectName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Optional<Subject> findBySubjectName(SubjectName subjectName);
}
