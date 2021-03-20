package com.modoodesigner.domain.application;

import com.modoodesigner.domain.application.commands.AttachmentUploadCommand;
import com.modoodesigner.domain.model.attachment.Attachment;

public interface AttachmentService {

    Attachment findById(Long id);

    Attachment upload(AttachmentUploadCommand command);

}
