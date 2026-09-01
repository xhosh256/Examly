package cephei.dev.ExamHelper.database.dto.user;

import lombok.Value;

@Value
public class UserLoginDto {
    String username;
    String password;
}
