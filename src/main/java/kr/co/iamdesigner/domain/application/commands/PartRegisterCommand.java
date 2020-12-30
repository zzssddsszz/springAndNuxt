package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.model.part.common.Material;
import kr.co.iamdesigner.domain.model.part.common.PlatingColor;
import kr.co.iamdesigner.domain.model.part.pendant.MountingType;
import kr.co.iamdesigner.domain.model.user.UserId;
import kr.co.iamdesigner.utils.IpAddress;
import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
public class PartRegisterCommand extends UserCommand {
    private String name;
    private int buyPrice;
    private int stock;
    private PlatingColor color;
    private Material material;

    @Builder
    public PartRegisterCommand(String name, int buyPrice, int stock, PlatingColor color, Material material, UserId userId, IpAddress ipAddress) {
        super(userId, ipAddress);
        this.name = name;
        this.buyPrice = buyPrice;
        this.stock = stock;
        this.color = color;
        this.material = material;
    }

}
