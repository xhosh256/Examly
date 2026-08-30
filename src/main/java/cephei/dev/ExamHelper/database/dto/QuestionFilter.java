package cephei.dev.ExamHelper.database.dto;

import lombok.Value;

import java.util.Set;

@Value
public class QuestionFilter {
    Set<Long> topicIds;
}
