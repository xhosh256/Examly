package cephei.dev.ExamHelper.database.dto.subject;

import cephei.dev.ExamHelper.database.dto.type.TaskTypeReadDto;
import cephei.dev.ExamHelper.database.entity.SubjectName;
import lombok.Value;

import java.util.List;

@Value
public class SubjectReadDto {
    Integer id;
    SubjectName subjectName;
    List<TaskTypeReadDto> taskTypes;
}
