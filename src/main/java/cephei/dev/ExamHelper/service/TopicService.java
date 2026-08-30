package cephei.dev.ExamHelper.service;

import cephei.dev.ExamHelper.database.dto.TopicReadDto;
import cephei.dev.ExamHelper.database.entity.SubjectName;
import cephei.dev.ExamHelper.database.repository.TopicRepository;
import cephei.dev.ExamHelper.mapper.TopicMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public List<TopicReadDto> findAllBySubjectNameAndTypeNumber(String subjectName, Integer typeNumber) {
        return topicRepository
                .findAllByQuestionTopics_Question_TaskType_NumberAndQuestionTopics_Question_TaskType_Subject_SubjectName(typeNumber, SubjectName.valueOf(subjectName.toUpperCase()))
                .stream().map(topicMapper::toReadDto).toList();
    }
}
