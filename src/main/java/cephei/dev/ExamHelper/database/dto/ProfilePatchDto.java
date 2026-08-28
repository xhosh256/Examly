package cephei.dev.ExamHelper.database.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfilePatchDto {

    private String firstname;
    private String lastname;
    private LocalDate birthDate;
}
