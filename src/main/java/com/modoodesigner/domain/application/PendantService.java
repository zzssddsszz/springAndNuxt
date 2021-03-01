package com.modoodesigner.domain.application;

import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.model.part.common.PartRegistrationException;
import com.modoodesigner.domain.model.part.pendant.Pendant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PendantService {

    Pendant findById(Long id);
    Pendant findByName(String name);

    void register(PendantRegisterCommand command) throws PartRegistrationException;

    Page<Pendant> findByAll(Pageable pageable);

}
