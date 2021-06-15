package com.modoodesigner.web.payload.paper;

import com.modoodesigner.domain.application.commands.PaperRegisterCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaperRegistrationPayload {
    private String image;
    private String object;
    private String email;
    private String pwd;
    private boolean encrypt;
    private String encryptImage;
    private String encryptObject;

    public PaperRegisterCommand toCommand() {
        return PaperRegisterCommand.builder()
                .image(image)
                .object(object)
                .email(email)
                .pwd(pwd)
                .encrypt(encrypt)
                .encryptImage(encryptImage)
                .encryptObject(encryptObject)
                .build();
    }
}
