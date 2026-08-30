package cephei.dev.ExamHelper.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(String formatted) {
        super(formatted);
    }
}
