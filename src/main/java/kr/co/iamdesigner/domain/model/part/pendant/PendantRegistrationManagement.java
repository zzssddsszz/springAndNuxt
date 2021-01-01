package kr.co.iamdesigner.domain.model.part.pendant;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.PartExistsException;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PendantRegistrationManagement {
    private final PendantRepository repository;
    private final JPAQueryFactory jpaQueryFactory;

    public Pendant register(PendantRegisterCommand command) throws PartRegistrationException {
        Pendant pendant = new Pendant(command);
        try {
            return repository.save(pendant);
        }catch (DataIntegrityViolationException e){
            throw new PartExistsException();
        }

    }


}
