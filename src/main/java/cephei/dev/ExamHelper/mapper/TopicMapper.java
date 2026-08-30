package cephei.dev.ExamHelper.mapper;

import cephei.dev.ExamHelper.database.dto.TopicReadDto;
import cephei.dev.ExamHelper.database.entity.Topic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    TopicReadDto toReadDto(Topic topic);
}
