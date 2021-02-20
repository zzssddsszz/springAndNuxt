package com.modoodesigner.domain.application;

import com.modoodesigner.domain.application.commands.PendantBulkRegisterCommand;
import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.model.part.common.PartRegistrationException;
import com.modoodesigner.domain.model.part.pendant.Pendant;

public interface PendantService {

    Pendant findById(Long id);
    Pendant findByName(String name);

    void register(PendantRegisterCommand command) throws PartRegistrationException;
    void registerBulk(PendantBulkRegisterCommand command) throws PartRegistrationException;


}
