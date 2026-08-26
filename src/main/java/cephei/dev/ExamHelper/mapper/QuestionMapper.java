package cephei.dev.ExamHelper.mapper;

import cephei.dev.ExamHelper.database.dto.QuestionReadDto;
import cephei.dev.ExamHelper.database.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionReadDto toReadDto(Question question);
}
