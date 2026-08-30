package cephei.dev.ExamHelper.service;

import cephei.dev.ExamHelper.database.dto.ProfilePatchDto;
import cephei.dev.ExamHelper.database.dto.ProfileReadDto;
import cephei.dev.ExamHelper.database.dto.UserDetailsImpl;
import cephei.dev.ExamHelper.database.entity.Profile;
import cephei.dev.ExamHelper.database.repository.ProfileRepository;
import cephei.dev.ExamHelper.exception.ProfileNotFoundException;
import cephei.dev.ExamHelper.mapper.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileReadDto findByUsername(String username) {
        return profileRepository.findByUser_Username(username)
                .map(profileMapper::toReadDto)
                .orElseThrow(() -> new UsernameNotFoundException("Username %s not found".formatted(username)));
    }

    @Transactional
    public ProfileReadDto updateMyProfile(UserDetails userDetails, ProfilePatchDto profilePatchDto) {
        Profile profile = profileRepository.findByUser_Username(userDetails.getUsername())
                .orElseThrow(() -> new ProfileNotFoundException("User %s doesnt have a profile"));

        profileMapper.updateFromPatch(profilePatchDto, profile);
        return profileMapper.toReadDto(profile);
    }
}
