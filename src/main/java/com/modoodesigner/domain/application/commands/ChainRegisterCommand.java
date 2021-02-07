package com.modoodesigner.domain.application.commands;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class ChainRegisterCommand extends PartRegisterCommand{

}
