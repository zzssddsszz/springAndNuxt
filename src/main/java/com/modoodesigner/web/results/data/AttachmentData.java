package com.modoodesigner.web.results.data;

import com.modoodesigner.domain.common.file.FTPSeverUploader;
import com.modoodesigner.domain.model.attachment.Attachment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentData {
    private Long id;
    private String filePath;
    private String location;
    private boolean thumbnailCreated;

    public AttachmentData(Attachment file) {
        id = file.getId();
        filePath = file.getFilePath();
        thumbnailCreated = file.isThumbnailCreated();
        if (file.isPublicFtp()) {
            location = FTPSeverUploader.getFtpPath(filePath);
        }
    }
}
