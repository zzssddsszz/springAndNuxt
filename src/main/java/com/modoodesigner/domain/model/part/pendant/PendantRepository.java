package com.modoodesigner.domain.model.part.pendant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PendantRepository extends JpaRepository<Pendant,Long> {
    Optional<Pendant> findByName(String name);
    boolean existsByCode(String code);
}