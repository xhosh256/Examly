package cephei.dev.ExamHelper.database.dto;

import lombok.Value;

@Value
public class ChangePasswordDto {

    String currentPassword;
    String newPassword;
}
