package com.modoodesigner.domain.application.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PaperRegisterCommand extends AnonymousCommand {
    private String image;
    private String object;
    private String email;
    private String pwd;
    private boolean encrypt;
    private String encryptImage;
    private String encryptObject;

}