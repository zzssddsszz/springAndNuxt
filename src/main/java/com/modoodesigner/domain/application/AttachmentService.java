package com.modoodesigner.domain.application;

import com.modoodesigner.domain.application.commands.AttachmentUploadCommand;
import com.modoodesigner.domain.model.attachment.Attachment;
import com.modoodesigner.utils.ImageUtils;

public interface AttachmentService {

    Attachment findById(Long id);

    Attachment upload(AttachmentUploadCommand command);

    Attachment imageUpload(AttachmentUploadCommand command) throws IllegalAccessException;

}
