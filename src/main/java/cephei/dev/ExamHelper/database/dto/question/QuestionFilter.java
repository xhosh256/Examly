package cephei.dev.ExamHelper.database.dto.question;

import lombok.Value;

import java.util.Set;

@Value
public class QuestionFilter {
    Set<Long> topicIds;
}
