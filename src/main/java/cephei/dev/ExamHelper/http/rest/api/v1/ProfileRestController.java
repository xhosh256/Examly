package cephei.dev.ExamHelper.http.rest.api.v1;

import cephei.dev.ExamHelper.database.dto.profile.ProfilePatchDto;
import cephei.dev.ExamHelper.database.dto.profile.ProfileReadDto;
import cephei.dev.ExamHelper.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/me")
    public ProfileReadDto updateMyProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid ProfilePatchDto profilePatchDto
    ) {
        return profileService.updateMyProfile(userDetails, profilePatchDto);
    }
}
