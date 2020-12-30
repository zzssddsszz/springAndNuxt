package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.model.part.common.PlatingColor;
import kr.co.iamdesigner.domain.model.part.pendant.MountingType;
import lombok.*;


@Getter
@EqualsAndHashCode
@ToString
public class PendantRegisterCommand extends PartRegisterCommand{
    private MountingType mountingType;
}
