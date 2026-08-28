package cephei.dev.ExamHelper.mapper;

import cephei.dev.ExamHelper.database.dto.ProfileReadDto;
import cephei.dev.ExamHelper.database.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileReadDto toReadDto(Profile profile);
}
