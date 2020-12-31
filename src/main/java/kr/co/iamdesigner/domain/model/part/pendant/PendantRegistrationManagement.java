package kr.co.iamdesigner.domain.model.part.pendant;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static kr.co.iamdesigner.domain.model.part.pendant.QPendant.*;

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
        Pendant pendant = jpaQueryFactory.selectFrom(QPendant.pendant)
                .where(QPendant.pendant.name.eq(cPendant.getName()),
                        QPendant.pendant.color.eq(cPendant.getColor())
                ).fetchOne();
        return ObjectUtils.isEmpty(pendant);
    }


}
