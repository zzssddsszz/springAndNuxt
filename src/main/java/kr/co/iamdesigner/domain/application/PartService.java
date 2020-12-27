package kr.co.iamdesigner.domain.application;

import kr.co.iamdesigner.domain.application.commands.BasePartRegisterCommand;
import kr.co.iamdesigner.domain.model.part.BasePart;
import kr.co.iamdesigner.domain.model.part.PartRegistrationException;
import kr.co.iamdesigner.domain.model.user.BaseRegistrationException;

public interface PartService {

    BasePart findById(Long id);
    BasePart findByName(String name);

    void register(BasePartRegisterCommand command) throws BaseRegistrationException, PartRegistrationException;


}
