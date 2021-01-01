package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.application.PendantService;
import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.BasePart;
import kr.co.iamdesigner.domain.model.part.common.PartExistsException;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import kr.co.iamdesigner.domain.model.part.pendant.Pendant;
import kr.co.iamdesigner.domain.model.part.pendant.PendantRegistrationManagement;
import kr.co.iamdesigner.domain.model.part.pendant.PendantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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
        log.info(command.toString());
        Pendant part = pendantRegistrationManagement.register(command);
    }
}
