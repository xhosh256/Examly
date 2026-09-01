package cephei.dev.ExamHelper.http.rest.api.v1;

import cephei.dev.ExamHelper.database.dto.user.UserLoginDto;
import cephei.dev.ExamHelper.database.dto.user.UserReadDto;
import cephei.dev.ExamHelper.database.dto.auth.UserRegisterDto;
import cephei.dev.ExamHelper.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationRestController {

    private final AuthService authService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto register(
            @RequestBody @Valid UserRegisterDto userRegisterDto
    ) {
        System.out.println(userRegisterDto);
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
