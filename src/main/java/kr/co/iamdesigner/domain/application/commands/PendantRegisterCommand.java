package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.model.part.common.Material;
import kr.co.iamdesigner.domain.model.part.common.PlatingColor;
import kr.co.iamdesigner.domain.model.part.pendant.MountingType;
import kr.co.iamdesigner.domain.model.user.UserId;
import kr.co.iamdesigner.utils.IpAddress;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
public class PendantRegisterCommand extends PartRegisterCommand{
    private MountingType mountingType;
}
