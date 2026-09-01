package cephei.dev.ExamHelper.database.dto.question;

import cephei.dev.ExamHelper.database.entity.AnswerType;
import lombok.Value;

@Value
public class QuestionReadDto {
    Long id;
    String question;
    String imageUrl;
    AnswerType answerType;
}
