package com.modoodesigner.domain.model.attachment;

import com.modoodesigner.domain.common.file.FileStorage;
import com.modoodesigner.domain.common.file.FileStorageResolver;
import com.modoodesigner.domain.common.file.TempFile;
import com.modoodesigner.domain.model.user.UserId;
import com.modoodesigner.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
@RequiredArgsConstructor
public class AttachmentManagement {

    private final FileStorageResolver fileStorageResolver;
    private final ThumbnailCreator thumbnailCreator;

    public Attachment save(UserId userId, MultipartFile file) {
        FileStorage fileStorage = fileStorageResolver.resolve();

        String filePath;
        String folder = "attachments";
        boolean thumbnailCreated = false;
        if (ImageUtils.isImage(file.getContentType())) {
            filePath = saveImage(fileStorage, folder, file);
        }
    }

    private String saveImage(FileStorage fileStorage, String folder, MultipartFile file) {
        TempFile tempImageFile = fileStorage.saveAsTempFile(folder, file);
        fileStorage.saveTempFile(tempImageFile);
        thumbnailCreator.create(fileStorage, tempImageFile);
    }


}
