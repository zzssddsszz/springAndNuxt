package com.modoodesigner.web.payload.paper;

import com.modoodesigner.domain.application.commands.PaperPendantRegisterCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaperPendantRegistrationPayload {
    Long paperId;
    String image;
    String object;

    public PaperPendantRegisterCommand toCommand() {
        return PaperPendantRegisterCommand.builder()
                .image(image)
                .object(object)
                .build();
    }
}
