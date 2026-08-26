package cephei.dev.ExamHelper.database.dto;

import lombok.Value;

@Value
public class ErrorResponse {
    String message;
    int status;
}
