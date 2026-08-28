package cephei.dev.ExamHelper.mapper;

import cephei.dev.ExamHelper.database.dto.QuestionReadDto;
import cephei.dev.ExamHelper.database.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(source = "taskType.answerType", target = "answerType")
    QuestionReadDto toReadDto(Question question);
}
