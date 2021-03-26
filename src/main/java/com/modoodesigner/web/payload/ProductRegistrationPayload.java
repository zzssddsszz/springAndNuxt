package com.modoodesigner.web.payload;

import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductRegistrationPayload {
    private String name;
    private List<Long> mainImages;
    private List<Long> contentImages;
    private String content;

    public ProductRegisterCommand toCommand() {
        ProductRegisterCommand command = ProductRegisterCommand.builder()
                .name(name)
                .mainImages(mainImages)
                .contentImages(contentImages)
                .content(content)
                .build();
        return command;
    }
}
