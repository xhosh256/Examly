package cephei.dev.ExamHelper.database.dto;

import lombok.Value;

@Value
public class QuestionReadDto {
    Long id;
    String question;
    String imageUrl;
}
