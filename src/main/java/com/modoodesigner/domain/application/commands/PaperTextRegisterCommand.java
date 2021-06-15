package com.modoodesigner.domain.application.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaperTextRegisterCommand extends AnonymousCommand {
    private Long paperId;
    private String image;
    private String object;
}
