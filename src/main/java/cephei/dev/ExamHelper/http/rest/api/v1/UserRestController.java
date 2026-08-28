package cephei.dev.ExamHelper.http.rest.api.v1;

import cephei.dev.ExamHelper.database.dto.ChangePasswordDto;
import cephei.dev.ExamHelper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserRestController {

    private final UserService userService;

    @PatchMapping("/change-password")
    public ResponseEntity<Void> changePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ChangePasswordDto changePasswordDto
    ) {
        userService.changePassword(userDetails, changePasswordDto);
        return ResponseEntity.ok().build();
    }
}
