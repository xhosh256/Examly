package cephei.dev.ExamHelper.database.dto;

import cephei.dev.ExamHelper.database.entity.SubjectName;
import lombok.Value;

import java.util.List;

@Value
public class SubjectReadDto {
    Integer id;
    SubjectName subjectName;
    List<TaskTypeReadDto> taskTypes;
}
