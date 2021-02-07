package com.modoodesigner.domain.application.commands;

import com.modoodesigner.domain.model.part.pendant.MountingType;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class PendantRegisterCommand extends PartRegisterCommand{
    private MountingType mountingType;
}
