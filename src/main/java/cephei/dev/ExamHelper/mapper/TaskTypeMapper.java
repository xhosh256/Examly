package cephei.dev.ExamHelper.mapper;

import cephei.dev.ExamHelper.database.dto.TaskTypeReadDto;
import cephei.dev.ExamHelper.database.entity.TaskType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskTypeMapper {

    TaskTypeReadDto toReadDto(TaskType taskType);
}
