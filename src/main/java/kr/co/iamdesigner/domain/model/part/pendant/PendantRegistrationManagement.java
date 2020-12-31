package kr.co.iamdesigner.domain.model.part.pendant;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.PartExistsException;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import static kr.co.iamdesigner.domain.model.part.pendant.QPendant.pendant;

@Component
@RequiredArgsConstructor
public class PendantRegistrationManagement {
    private final PendantRepository repository;
    private final JPAQueryFactory jpaQueryFactory;

    public Pendant register(PendantRegisterCommand command) throws PartRegistrationException {
        Pendant pendant = new Pendant(command);

        if (!existCheck(pendant)) {
            throw new PartExistsException();
        }
        return repository.save(pendant);
    }

    private boolean existCheck(Pendant cPendant) {
        Pendant result = jpaQueryFactory.selectFrom(pendant)
                .where(pendant.name.eq(cPendant.getName()),
                        pendant.color.eq(cPendant.getColor())
                ).fetchOne();
        return ObjectUtils.isEmpty(result);
    }


}
