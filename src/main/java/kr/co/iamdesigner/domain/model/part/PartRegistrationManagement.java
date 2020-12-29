package kr.co.iamdesigner.domain.model.part;

import kr.co.iamdesigner.domain.application.commands.BasePartRegisterCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartRegistrationManagement {
    private final PartRepository repository;


    public BasePart register(BasePartRegisterCommand command) throws PartRegistrationException {
        if (existsByBasePart(command.getName(),command.getColor(),command.getPartType())){
            throw new PartExistsException();
        }
        BasePart part = BasePart.builder()
                .name(command.getName())
                .color(command.getColor())
                .buyPrice(command.getBuyPrice())
                .stock(command.getStock())
                .partType(command.getPartType())
                .build();
        return repository.save(part);
    }

    private boolean existsByBasePart(String name, PlatingColor color, PartType partType) {
        return false;
    }


}
