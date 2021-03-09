package com.modoodesigner.web.results.data;

import com.modoodesigner.domain.model.attachment.Attachment;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AttachmentData {
    private Long id;
    private String filePath;
    private boolean thumbnailCreated;
    public AttachmentData(Attachment file) {
        id = file.getId();
        filePath = file.getFilePath();
        thumbnailCreated = file.isThumbnailCreated();
    }
}
