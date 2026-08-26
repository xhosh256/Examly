package cephei.dev.ExamHelper.mapper;

import cephei.dev.ExamHelper.database.dto.UserReadDto;
import cephei.dev.ExamHelper.database.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserReadDto toReadDto(User user);
}
