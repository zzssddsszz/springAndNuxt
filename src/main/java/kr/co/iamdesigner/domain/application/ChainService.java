package kr.co.iamdesigner.domain.application;

import kr.co.iamdesigner.domain.application.commands.ChainRegisterCommand;
import kr.co.iamdesigner.domain.model.part.chain.Chain;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;

import java.util.Optional;

public interface ChainService {

    Chain findById(Long id);
    Chain findByName(String name);

    void register(ChainRegisterCommand command) throws PartRegistrationException;
}
