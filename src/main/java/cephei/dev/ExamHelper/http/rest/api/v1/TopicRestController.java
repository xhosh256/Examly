package cephei.dev.ExamHelper.http.rest.api.v1;

import cephei.dev.ExamHelper.database.dto.TopicReadDto;
import cephei.dev.ExamHelper.database.repository.TopicRepository;
import cephei.dev.ExamHelper.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TopicRestController {

    private final TopicService topicService;

    @GetMapping("/subjects/{subjectName}/types/{typeNumber}/topics")
    public List<TopicReadDto> findAll(
            @PathVariable String subjectName,
            @PathVariable Integer typeNumber
    ) {
        return topicService.findAllBySubjectNameAndTypeNumber(
                subjectName,
                typeNumber
        );
    }
}
