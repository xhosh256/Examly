package cephei.dev.ExamHelper.http.rest.api.v1;

import cephei.dev.ExamHelper.database.dto.question.*;
import cephei.dev.ExamHelper.database.dto.user.UserDetailsImpl;
import cephei.dev.ExamHelper.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class QuestionRestController {

    private final QuestionService questionService;

    @PostMapping("/questions/{id}/check")
    public AnswerCheckResponse checkAnswer(
            @PathVariable Long id,
            @RequestBody AnswerCheckRequest answerCheckRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {
        return questionService.checkAnswer(id, answerCheckRequest, userDetails);
    }

    @GetMapping("/subjects/{subjectName}/types/{typeNumber}/questions")
    public Page<QuestionReadDto> findAllBySubjectNameAndTypeNumber(
            @PathVariable String subjectName,
            @PathVariable Integer typeNumber,
            QuestionFilter questionFilter,
            @PageableDefault(size = 5, page = 0) Pageable pageable
    ) {
        return questionService.findAllBySubjectNameAndTypeNumber(subjectName, typeNumber, questionFilter, pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/subjects/{subjectName}/types/{typeNumber}/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<QuestionReadDto> createQuestion(
            @RequestBody QuestionCreateDto questionCreateDto,
            @PathVariable String subjectName,
            @PathVariable Integer typeNumber
    ) {
        QuestionReadDto questionReadDto = questionService.createQuestion(questionCreateDto, subjectName, typeNumber);
        return ResponseEntity.ok(questionReadDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/topics/{topicId}/questions/{questionId}")
    public ResponseEntity<Void> connect(
            @PathVariable Long topicId,
            @PathVariable Long questionId
    ) {
        questionService.connect(topicId, questionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("questions/{id}/info")
    public ResponseEntity<QuestionInfo> getQuestionInfo(
            @PathVariable("id") Long questionId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        QuestionInfo status = questionService.getQuestionInfo(questionId, userDetails.getUser());
        return ResponseEntity.ok(status);
    }
}
