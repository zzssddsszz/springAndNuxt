package com.modoodesigner.web.payload.paper;

import com.modoodesigner.domain.application.commands.PaperTextRegisterCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaperTextRegistrationPayload {
    private Long paperId;
    private String image;
    private String object;

    public PaperTextRegisterCommand toCommand() {
        return PaperTextRegisterCommand.builder()
                .image(image)
                .object(object)
                .build();
    }
}
