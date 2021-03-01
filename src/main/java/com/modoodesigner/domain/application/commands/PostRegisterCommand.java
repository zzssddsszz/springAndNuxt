package com.modoodesigner.domain.application.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PostRegisterCommand {
    private String title;
    private String content;
}
