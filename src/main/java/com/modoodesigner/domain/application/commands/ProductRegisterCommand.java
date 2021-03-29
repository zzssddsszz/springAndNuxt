package com.modoodesigner.domain.application.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ProductRegisterCommand extends UserCommand{
    private String name;
    private Long[] mainImageIndex;
    private Long[] contentImage;
    private String content;
}
