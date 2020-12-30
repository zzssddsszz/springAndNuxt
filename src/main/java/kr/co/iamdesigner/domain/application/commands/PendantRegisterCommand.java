package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.model.part.common.Material;
import kr.co.iamdesigner.domain.model.part.common.PlatingColor;
import kr.co.iamdesigner.domain.model.part.pendant.MountingType;
import kr.co.iamdesigner.domain.model.user.UserId;
import kr.co.iamdesigner.utils.IpAddress;
import lombok.*;


@Getter  @Setter
@EqualsAndHashCode
@ToString
public class PendantRegisterCommand extends PartRegisterCommand{
    private MountingType mountingType;


    @Builder
    public PendantRegisterCommand(MountingType mountingType,String name, int buyPrice, int stock, PlatingColor color, Material material, UserId userId, IpAddress ipAddress) {
        super(name, buyPrice, stock, color, material, userId, ipAddress);
        this.mountingType = mountingType;
    }
}
