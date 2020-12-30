package kr.co.iamdesigner.domain.model.part.pendant;

import kr.co.iamdesigner.domain.model.part.common.BasePart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PendantRepository extends JpaRepository<BasePart,Long> {
    Optional<BasePart> findByName(String name);
}
