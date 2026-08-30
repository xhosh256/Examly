package cephei.dev.ExamHelper.mapper;

import cephei.dev.ExamHelper.database.dto.TopicCreateDto;
import cephei.dev.ExamHelper.database.dto.TopicReadDto;
import cephei.dev.ExamHelper.database.entity.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    TopicReadDto toReadDto(Topic topic);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "questionTopics", ignore = true)
    Topic toEntity(TopicCreateDto topicCreateDto);
}
