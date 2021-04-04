package com.modoodesigner.domain.application.commands;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class AttachmentUploadCommand extends UserCommand{
    private MultipartFile file;
    private boolean publicImage;

    public AttachmentUploadCommand(MultipartFile file) {
        this.file = file;
        this.publicImage = true;
    }

}
