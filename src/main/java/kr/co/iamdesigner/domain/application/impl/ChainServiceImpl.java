package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.application.ChainService;
import kr.co.iamdesigner.domain.application.commands.ChainRegisterCommand;
import kr.co.iamdesigner.domain.model.part.chain.Chain;
import kr.co.iamdesigner.domain.model.part.chain.ChainRegistrationManagement;
import kr.co.iamdesigner.domain.model.part.chain.ChainRepository;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import kr.co.iamdesigner.domain.model.part.pendant.PendantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ChainServiceImpl implements ChainService {
    private final ChainRegistrationManagement chainRegistrationManagement;
    private final ChainRepository repository;

    @Override
    public Chain findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Chain findByName(String name) {
        return repository.findByName(name).orElseThrow();
    }

    @Override
    public void register(ChainRegisterCommand command) throws PartRegistrationException {
        Chain chain = chainRegistrationManagement.register(command);
    }
}
