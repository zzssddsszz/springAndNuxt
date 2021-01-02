package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.model.part.common.Material;
import kr.co.iamdesigner.domain.model.part.common.PlatingColor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class PartRegisterCommand extends UserCommand {
    private String name;
    private String code;
    private int buyPrice;
    private int stock;
    private PlatingColor color;
    private Material material;


}
