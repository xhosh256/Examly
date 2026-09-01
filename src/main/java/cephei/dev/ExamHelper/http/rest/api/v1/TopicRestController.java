package cephei.dev.ExamHelper.http.rest.api.v1;

import cephei.dev.ExamHelper.database.dto.topic.TopicCreateDto;
import cephei.dev.ExamHelper.database.dto.topic.TopicReadDto;
import cephei.dev.ExamHelper.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/topics")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TopicReadDto> createTopic(
            @RequestBody TopicCreateDto topicCreateDto
    ) {
        TopicReadDto topic = topicService.create(topicCreateDto);

        return ResponseEntity.ok(topic);
    }


}
