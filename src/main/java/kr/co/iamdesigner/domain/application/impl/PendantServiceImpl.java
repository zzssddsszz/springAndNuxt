package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.application.PendantService;
import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.BasePart;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import kr.co.iamdesigner.domain.model.part.pendant.PendantRegistrationManagement;
import kr.co.iamdesigner.domain.model.part.pendant.PendantRepository;
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

    public BasePart findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public BasePart findByName(String name) {
        return repository.findByName(name).orElseThrow();
    }

    @Override
    public void register(PendantRegisterCommand command) throws PartRegistrationException {
        log.info(command.toString());
        BasePart part = pendantRegistrationManagement.register(command);
    }
}
