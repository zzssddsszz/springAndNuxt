package com.modoodesigner.domain.application.commands;

import com.modoodesigner.domain.model.part.common.PlatingColor;
import com.modoodesigner.domain.model.part.common.Material;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class PartRegisterCommand extends UserCommand {
    private String name;
    private int buyPrice;
    private int stock;
    private PlatingColor color;
    private Material material;


}
