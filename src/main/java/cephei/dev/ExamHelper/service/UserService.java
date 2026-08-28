package cephei.dev.ExamHelper.service;

import cephei.dev.ExamHelper.database.dto.*;
import cephei.dev.ExamHelper.database.entity.User;
import cephei.dev.ExamHelper.database.repository.UserRepository;
import cephei.dev.ExamHelper.exception.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User maybeUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username %s not found".formatted(username)));

        return new UserDetailsImpl(maybeUser);
    }

    @Transactional
    public void changePassword(UserDetails userDetails, ChangePasswordDto changePasswordDto) {

        if(!passwordEncoder.matches(
                changePasswordDto.getCurrentPassword(),
                userDetails.getPassword()
        )) {
            throw new WrongPasswordException("Wrong current password!");
        }

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username %s not found"
                        .formatted(userDetails.getUsername())));

        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
    }
}
