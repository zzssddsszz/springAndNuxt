package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.model.part.PartType;
import kr.co.iamdesigner.domain.model.part.PlatingColor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@EqualsAndHashCode(callSuper = false)
public class BasePartRegisterCommand extends UserCommand{
    private String name;
    private int buyPrice;
    private int sellPrice;
    private int stock;
    private PartType partType;
    private PlatingColor color;
}
