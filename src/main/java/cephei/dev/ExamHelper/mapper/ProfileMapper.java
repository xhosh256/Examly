package cephei.dev.ExamHelper.mapper;

import cephei.dev.ExamHelper.database.dto.profile.ProfilePatchDto;
import cephei.dev.ExamHelper.database.dto.profile.ProfileReadDto;
import cephei.dev.ExamHelper.database.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProfileMapper {

    ProfileReadDto toReadDto(Profile profile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateFromPatch(ProfilePatchDto dto, @MappingTarget Profile profile);
}
