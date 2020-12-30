package kr.co.iamdesigner.domain.model.part.pendant;

import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PendantRegistrationManagement {
    private final PendantRepository repository;

    public Pendant register(PendantRegisterCommand command) throws PartRegistrationException {
        Pendant pendant = new Pendant(command);

        if (existCheck(pendant)){
            throw new PartExistsException();
        }
        return repository.save(pendant);
    }

    private boolean existCheck(Pendant pendant) {

        return false;
    }


}
