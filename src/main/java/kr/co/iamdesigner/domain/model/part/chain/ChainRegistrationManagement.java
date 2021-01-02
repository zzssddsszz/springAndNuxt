package kr.co.iamdesigner.domain.model.part.chain;

import kr.co.iamdesigner.domain.application.commands.ChainRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChainRegistrationManagement {
    private final ChainRepository repository;

    public Chain register(ChainRegisterCommand command) throws PartRegistrationException{
        Chain chain = new Chain(command);
        try{
            return repository.save(chain);
        }catch (DataIntegrityViolationException e){
            throw new PartRegistrationException();
        }
    }
}
