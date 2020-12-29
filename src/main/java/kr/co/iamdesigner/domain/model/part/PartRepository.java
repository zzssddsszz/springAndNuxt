package kr.co.iamdesigner.domain.model.part;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartRepository extends JpaRepository<BasePart,Long> {
    Optional<BasePart> findByName(String name);
}
