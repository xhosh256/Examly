package cephei.dev.ExamHelper.database.dto.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfilePatchDto {

    @NotBlank(message = "Firstname cannot be blank")
    @Size(max = 128)
    private String firstname;

    @Size(max = 128)
    private String lastname;

    @Past
    private LocalDate birthDate;
}
