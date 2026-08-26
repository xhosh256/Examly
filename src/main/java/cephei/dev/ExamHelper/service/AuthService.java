package cephei.dev.ExamHelper.service;

import cephei.dev.ExamHelper.database.dto.LoginReadDto;
import cephei.dev.ExamHelper.database.dto.UserLoginDto;
import cephei.dev.ExamHelper.database.dto.UserReadDto;
import cephei.dev.ExamHelper.database.dto.UserRegisterDto;
import cephei.dev.ExamHelper.database.entity.Role;
import cephei.dev.ExamHelper.database.entity.User;
import cephei.dev.ExamHelper.database.repository.UserRepository;
import cephei.dev.ExamHelper.exception.WrongUsernameOrPassword;
import cephei.dev.ExamHelper.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Transactional
    public UserReadDto register(UserRegisterDto userRegisterDto) {
        User user = User.builder()
                .username(userRegisterDto.getUsername())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .role(Role.USER).build();
        return userMapper.toReadDto(userRepository.save(user));
    }

    public String login(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getUsername(),
                        userLoginDto.getPassword())
        );

        if(authentication.isAuthenticated() && authentication.getPrincipal() != null) {
            return jwtService.generateToken(userLoginDto.getUsername());
        }

        throw new WrongUsernameOrPassword("Wrong username or password");
    }
}
