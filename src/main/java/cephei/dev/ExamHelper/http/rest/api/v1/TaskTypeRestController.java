package cephei.dev.ExamHelper.http.rest.api.v1;

import cephei.dev.ExamHelper.database.dto.type.TaskTypeReadDto;
import cephei.dev.ExamHelper.service.TaskTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/subjects/{subjectName}/types")
public class TaskTypeRestController {

    private final TaskTypeService taskTypeService;

    @GetMapping("/{number}")
    public ResponseEntity<TaskTypeReadDto> findByNumber(
            @PathVariable String subjectName,
            @PathVariable Integer number
    ) {
        return taskTypeService
                .findBySubjectNameAndNumber(subjectName.toUpperCase(), number)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
