package cephei.dev.ExamHelper.mapper;

import cephei.dev.ExamHelper.database.dto.subject.SubjectReadDto;
import cephei.dev.ExamHelper.database.entity.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectReadDto toReadDto(Subject subject);
}
