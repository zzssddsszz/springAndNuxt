package com.modoodesigner.domain.application.impl;

import com.modoodesigner.domain.application.PendantService;
import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.model.part.pendant.PendantRegistrationManagement;
import com.modoodesigner.domain.model.part.pendant.PendantRepository;
import com.modoodesigner.domain.model.part.common.PartRegistrationException;
import com.modoodesigner.domain.model.part.pendant.Pendant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PendantServiceImpl implements PendantService {
    private final PendantRegistrationManagement pendantRegistrationManagement;
    private final PendantRepository repository;

    public Pendant findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Pendant findByName(String name) {
        return repository.findByName(name).orElseThrow();
    }

    @Override
    public void register(PendantRegisterCommand command) throws PartRegistrationException {
        Pendant part = pendantRegistrationManagement.register(command);
    }
}
