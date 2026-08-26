package cephei.dev.ExamHelper.http.rest.api.v1;

import cephei.dev.ExamHelper.database.dto.SubjectReadDto;
import cephei.dev.ExamHelper.database.entity.SubjectName;
import cephei.dev.ExamHelper.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subjects")
@RequiredArgsConstructor
public class SubjectRestController {

    private final SubjectService subjectService;

    @GetMapping
    public List<SubjectReadDto> findAll() {
        return subjectService.findAll();
    }

    @GetMapping("/{subjectName}")
    public ResponseEntity<SubjectReadDto> findBySubjectName(@PathVariable String subjectName) {
        return subjectService.findBySubjectName(subjectName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
