package kr.co.iamdesigner.web.payload;

import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.Material;
import kr.co.iamdesigner.domain.model.part.common.PlatingColor;
import kr.co.iamdesigner.domain.model.part.pendant.MountingType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.EnumUtils;

@Getter
@Setter @Builder
public class PendantRegistrationPayload {

    private String name;
    private int buyPrice;
    private int stock;
    private String color;
    private String material;
    private String mountingType;

    public PendantRegisterCommand toCommand() {
        PendantRegisterCommand command = PendantRegisterCommand.builder()
                .name(name)
                .buyPrice(buyPrice)
                .stock(stock)
                .color(PlatingColor.valueOf(color))
                .material(Material.valueOf(material))
                .mountingType(MountingType.valueOf(mountingType))
                .build();
        return command;
    }
}