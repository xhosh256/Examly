package cephei.dev.ExamHelper.service;

import cephei.dev.ExamHelper.database.dto.subject.SubjectReadDto;
import cephei.dev.ExamHelper.database.entity.SubjectName;
import cephei.dev.ExamHelper.database.repository.SubjectRepository;
import cephei.dev.ExamHelper.mapper.SubjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public List<SubjectReadDto> findAll() {
        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::toReadDto)
                .toList();
    }

    public Optional<SubjectReadDto> findBySubjectName(String subjectName) {
        return subjectRepository.findBySubjectName(SubjectName.valueOf(subjectName.toUpperCase()))
                .map(subjectMapper::toReadDto);
    }
}
