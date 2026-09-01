package cephei.dev.ExamHelper.database.dto.question;

public record QuestionCreateDto (

        String question,
        String imageUrl,
        String answer
) {}
