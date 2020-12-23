package kr.co.iamdesigner.domain.model.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAddress(String emailAddress);
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmailAddress(String emailAddress);
}
