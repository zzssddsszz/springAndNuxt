package kr.co.iamdesigner.domain.model.part;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartRegistrationManagement {
    private final PartRepository repository;
}
