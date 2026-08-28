package cephei.dev.ExamHelper.database.repository;

import cephei.dev.ExamHelper.database.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Optional<Profile> findByUser_Username(String username);
}
