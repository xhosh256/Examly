package cephei.dev.ExamHelper.http.rest.api.v1;

import cephei.dev.ExamHelper.database.dto.ProfileReadDto;
import cephei.dev.ExamHelper.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/profiles")
public class ProfileRestController {

    private final ProfileService profileService;

    @GetMapping("/me")
    public ProfileReadDto findMe(
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        return profileService.findByUsername(userDetails.getUsername());
    }

}
