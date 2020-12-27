package kr.co.iamdesigner.domain.model.part;

import kr.co.iamdesigner.domain.application.commands.BasePartRegisterCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class PartRegistrationManagement {
    private final PartRepository repository;

    public BasePart register(BasePartRegisterCommand command) throws PartRegistrationException {
        if (repository.existsByNameAndColor(command.getName(),command.getColor())){
            throw new PartExistsException();
        }
        BasePart part = BasePart.builder()
                .name(command.getName())
                .color(command.getColor())
                .buyPrice(command.getBuyPrice())
                .sellPrice(command.getSellPrice())
                .stock(command.getStock())
                .partType(command.getPartType())
                .build();
        return repository.save(part);
    }


}
