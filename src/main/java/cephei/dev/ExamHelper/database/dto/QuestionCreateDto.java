package cephei.dev.ExamHelper.database.dto;

public record QuestionCreateDto (
        String question,
        String imageUrl,
        String answer
) {}
