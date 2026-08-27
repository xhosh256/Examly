package cephei.dev.ExamHelper.http.rest.api.v1;

import cephei.dev.ExamHelper.database.dto.AnswerCheckRequest;
import cephei.dev.ExamHelper.database.dto.AnswerCheckResponse;
import cephei.dev.ExamHelper.database.dto.QuestionReadDto;
import cephei.dev.ExamHelper.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class QuestionRestController {

    private final QuestionService questionService;

    @PostMapping("/questions/{id}/check")
    public AnswerCheckResponse checkAnswer(
            @PathVariable Long id,
            @RequestBody AnswerCheckRequest answerCheckRequest
            ) {
        return questionService.checkAnswer(id, answerCheckRequest);
    }

//    @GetMapping("/types/{typeId}/questions")
//    public Page<QuestionReadDto> findAllByTaskTypeId(
//            @PathVariable Long typeId,
//            @PageableDefault(size = 5, page = 0) Pageable pageable
//    ) {
//        return questionService.findAllByTaskTypeId(typeId, pageable);
//    }

    @GetMapping("/subjects/{subjectName}/types/{typeNumber}/questions")
    public Page<QuestionReadDto> findAllBySubjectNameAndTypeNumber(
            @PathVariable String subjectName,
            @PathVariable Integer typeNumber,
            @PageableDefault(size = 5, page = 0) Pageable pageable
    ) {
        return questionService.findAllBySubjectNameAndTypeNumber(subjectName, typeNumber, pageable);
    }

}
