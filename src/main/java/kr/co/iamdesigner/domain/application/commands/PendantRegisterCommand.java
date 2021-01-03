package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.model.part.pendant.MountingType;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
public class PendantRegisterCommand extends PartRegisterCommand{
    private MountingType mountingType;
}
