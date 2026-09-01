package cephei.dev.ExamHelper.mapper;

import cephei.dev.ExamHelper.database.dto.question.QuestionCreateDto;
import cephei.dev.ExamHelper.database.dto.question.QuestionReadDto;
import cephei.dev.ExamHelper.database.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(source = "taskType.answerType", target = "answerType")
    QuestionReadDto toReadDto(Question question);

    @Mapping(target = "taskType", ignore = true)
    @Mapping(target = "questionTopics", ignore = true)
    @Mapping(target = "id", ignore = true)
    Question toEntity(QuestionCreateDto questionCreateDto);
}
