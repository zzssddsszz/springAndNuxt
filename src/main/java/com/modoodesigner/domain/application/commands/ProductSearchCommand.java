package com.modoodesigner.domain.application.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter @Setter
public class ProductSearchCommand extends AnonymousCommand {
    private String search;
    private Pageable pageable;

    @Builder
    public ProductSearchCommand(String search, Pageable pageable) {
        this.search = search;
        this.pageable = pageable;
    }
}
