package kr.co.iamdesigner.domain.model.part.common;

import kr.co.iamdesigner.domain.application.commands.PartRegisterCommand;
import kr.co.iamdesigner.domain.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BasePart extends BaseEntity {

    public BasePart(PartRegisterCommand command) {
        buyPrice = command.getBuyPrice();
        name = command.getName();
        code = command.getCode();
        stock = command.getStock();
        color = command.getColor();
        material = command.getMaterial();
    }

    private String name;

    private String code;

    private int buyPrice;

    private int sellPrice;

    private int stock;

    @Enumerated(EnumType.STRING)
    private PlatingColor color;

    @Enumerated(EnumType.STRING)
    private Material material;




}
