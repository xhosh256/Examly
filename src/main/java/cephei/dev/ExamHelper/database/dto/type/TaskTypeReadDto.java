package cephei.dev.ExamHelper.database.dto.type;

import lombok.Value;


@Value
public class TaskTypeReadDto {
    Long id;
    Integer number;
    String name;
}
