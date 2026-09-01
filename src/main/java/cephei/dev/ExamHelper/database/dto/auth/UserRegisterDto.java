package cephei.dev.ExamHelper.database.dto.auth;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserRegisterDto {

    @Size(min = 4, max = 128, message = "Username size should be between 4 and 128 characters")
    @NotEmpty
    String username;

    @Size(min = 8, max = 128, message = "Password must be between 4 and 128 characters")
    @NotEmpty
    String password;

    @NotBlank(message = "Firstname cannot be blank")
    @Size(max = 128)
    String firstname;

    @Size(max = 128)
    String lastname;

    @Past
    LocalDate birthDate;
}
