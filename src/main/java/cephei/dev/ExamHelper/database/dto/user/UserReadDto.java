package cephei.dev.ExamHelper.database.dto.user;

import cephei.dev.ExamHelper.database.entity.Role;
import lombok.Value;

@Value
public class UserReadDto {
    Integer id;
    String username;
    Role role;
}
