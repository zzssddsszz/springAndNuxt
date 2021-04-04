package com.modoodesigner.web.payload;

import com.modoodesigner.domain.application.commands.ProductSearchCommand;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductSearchPayload {
    private String name;

    public ProductSearchCommand toCommand() {
        return ProductSearchCommand.builder()
                .search(name)
                .build();
    }
}
