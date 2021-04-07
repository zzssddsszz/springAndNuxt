package com.modoodesigner.domain.application.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter @Setter
public class ProductRegisterCommand extends UserCommand{
    private String name;
    private List<Long> mainImages;
    private String content;
}
