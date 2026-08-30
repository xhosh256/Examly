package cephei.dev.ExamHelper.service;

import cephei.dev.ExamHelper.database.dto.*;
import cephei.dev.ExamHelper.database.entity.*;
import cephei.dev.ExamHelper.database.repository.*;
import cephei.dev.ExamHelper.database.specification.QuestionSpecification;
import cephei.dev.ExamHelper.exception.QuestionNotFoundException;
import cephei.dev.ExamHelper.exception.TaskTypeNotFound;
import cephei.dev.ExamHelper.exception.TopicNotFoundException;
import cephei.dev.ExamHelper.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final TaskTypeRepository taskTypeRepository;
    private final TopicRepository topicRepository;
    private final QuestionTopicRepository questionTopicRepository;
    private final UserRepository userRepository;

    @Transactional
    public AnswerCheckResponse checkAnswer(Long id, AnswerCheckRequest answerCheckRequest, UserDetailsImpl userDetails) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question with id %d not found".formatted(id)));

        if(!question.getAnswer().equals(answerCheckRequest.getAnswer())) {
            return new AnswerCheckResponse(
                    QuestionAnswerStatus.INCORRECT
            );
        }
        User user = userDetails.getUser();

        if(!user.getSolvedQuestions().contains(question)) {
            user.solve(question);
            userRepository.save(user);
        }

        return new AnswerCheckResponse(
                QuestionAnswerStatus.CORRECT
        );
    }


    public Page<QuestionReadDto> findAllByTaskTypeId(Long typeId, Pageable pageable) {
        return questionRepository.findAllByTaskType_Id(typeId, pageable)
                .map(questionMapper::toReadDto);
    }

    public Page<QuestionReadDto> findAllBySubjectNameAndTypeNumber(
            String subjectName,
            Integer typeNumber,
            QuestionFilter questionFilter,
            Pageable pageable
    ) {
        Specification<Question> specification = Specification
                .where(QuestionSpecification.hasTopicIds((questionFilter.getTopicIds())))
                .and(QuestionSpecification.hasSubjectName(SubjectName.valueOf(subjectName.toUpperCase())))
                .and(QuestionSpecification.hasTypeNumber(typeNumber));

        return questionRepository
                .findAll(
                         specification, pageable
                ).map(questionMapper::toReadDto);
    }

    @Transactional
    public QuestionReadDto createQuestion(QuestionCreateDto questionCreateDto, String subjectName, Integer typeNumber) {
        TaskType maybeTaskType = taskTypeRepository.findBySubject_SubjectNameAndNumber(
                SubjectName.valueOf(subjectName.toUpperCase()),
                typeNumber
        ).orElseThrow(() -> new TaskTypeNotFound("Task type №%d of %s not found".formatted(typeNumber, subjectName)));

        Question question = questionMapper.toEntity(questionCreateDto);
        maybeTaskType.addQuestion(question);
        questionRepository.save(question);
        return questionMapper.toReadDto(question);
    }

    @Transactional
    public void connect(Long topicId, Long questionId) {
        Topic maybeTopic = topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException("Topic with id %d not found".formatted(topicId)));
        Question maybeQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException("Question with id %d not found".formatted(questionId)));

        QuestionTopic questionTopic = new QuestionTopic();
        questionTopic.setQuestion(maybeQuestion);
        questionTopic.setTopic(maybeTopic);
        questionTopicRepository.save(questionTopic);
    }


    public QuestionInfo getQuestionInfo(Long questionId, User user) {
        Question maybeQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException("Question with id %d not found".formatted(questionId)));


        return new QuestionInfo(
                user.getSolvedQuestions().contains(maybeQuestion)
        );
    }
}
