package com.modoodesigner.domain.application.commands;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class AttachmentUploadCommand extends UserCommand{
    private MultipartFile file;

    public AttachmentUploadCommand(MultipartFile file) {
        this.file = file;
    }

}
