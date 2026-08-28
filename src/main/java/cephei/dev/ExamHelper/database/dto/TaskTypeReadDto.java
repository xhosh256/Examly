package cephei.dev.ExamHelper.database.dto;

import cephei.dev.ExamHelper.database.entity.AnswerType;
import lombok.Value;


@Value
public class TaskTypeReadDto {
    Long id;
    Integer number;
    String name;
}
