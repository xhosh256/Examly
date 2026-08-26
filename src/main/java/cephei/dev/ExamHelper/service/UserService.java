package cephei.dev.ExamHelper.service;

import cephei.dev.ExamHelper.database.dto.*;
import cephei.dev.ExamHelper.database.entity.User;
import cephei.dev.ExamHelper.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User maybeUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username %s not found".formatted(username)));

        return new UserDetailsImpl(maybeUser);
    }
}
