package cephei.dev.ExamHelper.database.dto.profile;

import lombok.Value;

import java.time.LocalDate;

@Value
public class ProfileReadDto {
    String firstname;
    String lastname;
    LocalDate birthDate;
}
