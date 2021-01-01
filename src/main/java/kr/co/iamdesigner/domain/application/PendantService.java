package kr.co.iamdesigner.domain.application;

import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import kr.co.iamdesigner.domain.model.part.pendant.Pendant;

public interface PendantService {

    Pendant findById(Long id);
    Pendant findByName(String name);

    void register(PendantRegisterCommand command) throws PartRegistrationException;


}
