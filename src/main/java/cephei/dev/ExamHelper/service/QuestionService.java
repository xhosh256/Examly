package cephei.dev.ExamHelper.service;

import cephei.dev.ExamHelper.database.dto.*;
import cephei.dev.ExamHelper.database.entity.Question;
import cephei.dev.ExamHelper.database.entity.SubjectName;
import cephei.dev.ExamHelper.database.repository.QuestionRepository;
import cephei.dev.ExamHelper.database.specification.QuestionSpecification;
import cephei.dev.ExamHelper.exception.QuestionNotFoundException;
import cephei.dev.ExamHelper.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public AnswerCheckResponse checkAnswer(Long id, AnswerCheckRequest answerCheckRequest) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question with id %d not found".formatted(id)));

        if(!question.getAnswer().equals(answerCheckRequest.getAnswer())) {
            System.out.println("INCORRECT");
            return new AnswerCheckResponse(
                    QuestionAnswerStatus.INCORRECT
            );
        }

        System.out.println("CORRECT");
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
}
