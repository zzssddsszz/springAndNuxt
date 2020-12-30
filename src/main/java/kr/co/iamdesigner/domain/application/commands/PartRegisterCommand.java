package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.model.part.common.Material;
import kr.co.iamdesigner.domain.model.part.common.PlatingColor;
import kr.co.iamdesigner.domain.model.part.pendant.MountingType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class PartRegisterCommand extends UserCommand {
    private String name;
    private int buyPrice;
    private int stock;
    private PlatingColor color;
    private Material material;
}
