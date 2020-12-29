package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.application.PartService;
import kr.co.iamdesigner.domain.application.commands.BasePartRegisterCommand;
import kr.co.iamdesigner.domain.model.part.BasePart;
import kr.co.iamdesigner.domain.model.part.PartRegistrationException;
import kr.co.iamdesigner.domain.model.part.PartRegistrationManagement;
import kr.co.iamdesigner.domain.model.part.PartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PartServiceImpl implements PartService {
    private final PartRegistrationManagement partRegistrationManagement;
    private final PartRepository repository;

    public BasePart findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public BasePart findByName(String name) {
        return repository.findByName(name).orElseThrow();
    }

    @Override
    public void register(BasePartRegisterCommand command) throws PartRegistrationException {
        log.info(command.toString());
        BasePart part = partRegistrationManagement.register(command);
    }
}
