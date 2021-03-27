package com.modoodesigner.web.results.data;

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
            location = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20120611_12%2Flive_1975_1339400378420oSEhg_JPEG%2F%25B0%25A8%25BC%25BA%25BB%25E7%25C1%25F8_400x300.jpg&type=sc960_832";
        }
    }
}
