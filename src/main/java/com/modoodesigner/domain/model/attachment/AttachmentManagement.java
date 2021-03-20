package com.modoodesigner.domain.model.attachment;

import com.modoodesigner.domain.common.file.FileStorage;
import com.modoodesigner.domain.common.file.FileStorageResolver;
import com.modoodesigner.domain.common.file.TempFile;
import com.modoodesigner.domain.model.user.User;
import com.modoodesigner.domain.model.user.UserId;
import com.modoodesigner.domain.model.user.UserRepository;
import com.modoodesigner.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@Component
@Slf4j
@RequiredArgsConstructor
public class AttachmentManagement {

    private final FileStorageResolver fileStorageResolver;
    private final ThumbnailCreator thumbnailCreator;
    private final AttachmentRepository attachmentRepository;
    private final UserRepository userRepository;

    public Attachment save(Long userId, MultipartFile file) {
        FileStorage fileStorage = fileStorageResolver.resolve();

        String filePath;
        String folder = "attachments";
        boolean thumbnailCreated = false;
        if (ImageUtils.isImage(file.getContentType())) {
            filePath = saveImage(fileStorage, folder, file);
            thumbnailCreated = true;
        }else {
            filePath = fileStorage.saveUploaded(folder, file);
        }

        Attachment attachment = Attachment.builder()
                .user(userRepository.findById(userId).orElseThrow())
                .fileName(file.getOriginalFilename())
                .filePath(filePath)
                .thumbnailCreated(thumbnailCreated)
                .build();

        return attachmentRepository.save(attachment);
    }

    private String saveImage(FileStorage fileStorage, String folder, MultipartFile file) {
        TempFile tempImageFile = fileStorage.saveAsTempFile(folder, file);
        fileStorage.saveTempFile(tempImageFile);
        thumbnailCreator.create(fileStorage, tempImageFile);

        try {
            Files.delete(tempImageFile.getFile().toPath());
        } catch (IOException e) {
            log.error("임시파일 삭제에 실패했습니다. '" + tempImageFile.getFile().getAbsolutePath() + "'",e);
        }
        return tempImageFile.getFileRelativePath();
    }


}