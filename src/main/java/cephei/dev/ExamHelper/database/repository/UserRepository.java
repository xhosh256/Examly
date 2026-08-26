package cephei.dev.ExamHelper.database.repository;

import cephei.dev.ExamHelper.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
