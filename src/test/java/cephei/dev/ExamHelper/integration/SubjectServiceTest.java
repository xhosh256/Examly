package cephei.dev.ExamHelper.integration;

import cephei.dev.ExamHelper.database.dto.SubjectReadDto;
import cephei.dev.ExamHelper.database.entity.Subject;
import cephei.dev.ExamHelper.database.entity.SubjectName;
import cephei.dev.ExamHelper.service.SubjectService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class SubjectServiceTest {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findAll() {
        Subject math = Subject.builder()
                .subjectName(SubjectName.MATH)
                .build();
        Subject inf = Subject.builder()
                .subjectName(SubjectName.INFORMATICS)
                .build();

        entityManager.persist(math);
        entityManager.persist(inf);

        entityManager.clear();

        List<SubjectReadDto> maybeSubjects = subjectService.findAll();
        assertFalse(maybeSubjects.isEmpty());
        assertThat(maybeSubjects).hasSize(2);
        assertEquals(SubjectName.MATH, maybeSubjects.getFirst().getSubjectName());
        assertEquals(SubjectName.INFORMATICS, maybeSubjects.get(1).getSubjectName());

    }

    @Test
    void findBySubjectName() {
        Subject math = Subject.builder()
                .subjectName(SubjectName.MATH)
                .build();
        Subject inf = Subject.builder()
                .subjectName(SubjectName.INFORMATICS)
                .build();

        entityManager.persist(math);
        entityManager.persist(inf);

        entityManager.clear();

        Optional<SubjectReadDto> maybeSubject1 = subjectService.findBySubjectName("math");
        Optional<SubjectReadDto> maybeSubject2 = subjectService.findBySubjectName("RUSSIAN_LANGUAGE");

        assertThat(maybeSubject1).isPresent();
        assertEquals(SubjectName.MATH ,maybeSubject1.get().getSubjectName());
        assertThat(maybeSubject2).isEmpty();
    }
}
