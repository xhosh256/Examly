package cephei.dev.ExamHelper.http.rest.api.v1;

import cephei.dev.ExamHelper.database.dto.LoginReadDto;
import cephei.dev.ExamHelper.database.dto.UserLoginDto;
import cephei.dev.ExamHelper.database.dto.UserReadDto;
import cephei.dev.ExamHelper.database.dto.UserRegisterDto;
import cephei.dev.ExamHelper.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationRestController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserReadDto register(
            @RequestBody UserRegisterDto userRegisterDto
    ) {
        return authService.register(userRegisterDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody UserLoginDto userLoginDto
    ) {
        String jwtToken = authService.login(userLoginDto);

        return ResponseEntity.ok(jwtToken);
    }
}
