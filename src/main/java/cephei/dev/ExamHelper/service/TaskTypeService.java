package cephei.dev.ExamHelper.service;

import cephei.dev.ExamHelper.database.dto.type.TaskTypeReadDto;
import cephei.dev.ExamHelper.database.entity.SubjectName;
import cephei.dev.ExamHelper.database.repository.TaskTypeRepository;
import cephei.dev.ExamHelper.mapper.TaskTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskTypeService {

    private final TaskTypeRepository taskTypeRepository;
    private final TaskTypeMapper taskTypeMapper;

    public Optional<TaskTypeReadDto> findBySubjectNameAndNumber(String subjectName, Integer number) {
        return taskTypeRepository
                .findBySubject_SubjectNameAndNumber(SubjectName.valueOf(subjectName), number)
                .map(taskTypeMapper::toReadDto);
    }
}
