package kr.co.iamdesigner.domain.application;

import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.BasePart;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import kr.co.iamdesigner.domain.model.user.BaseRegistrationException;

public interface PendantService {

    BasePart findById(Long id);
    BasePart findByName(String name);

    void register(PendantRegisterCommand command) throws BaseRegistrationException, PartRegistrationException;


}
