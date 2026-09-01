package cephei.dev.ExamHelper.database.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class ChangePasswordDto {

    String currentPassword;

    @Size(min = 8, max = 128, message = "Password must be between 4 and 128 characters")
    @NotEmpty
    String newPassword;
}
