package com.modoodesigner.domain.model.attachment;

import com.modoodesigner.domain.common.file.FileStorage;
import com.modoodesigner.domain.common.file.TempFile;
import com.modoodesigner.utils.ImageUtils;
import com.modoodesigner.utils.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class ThumbnailCreator {

    private final static Set<String> SUPPORTED_EXTENSIONS = new HashSet<>();
    private final static int MAX_WIDTH = 300;
    private final static int MAX_HEIGHT = 300;

    static {
        SUPPORTED_EXTENSIONS.add("png");
        SUPPORTED_EXTENSIONS.add("jpg");
        SUPPORTED_EXTENSIONS.add("jpeg");
    }

    private final ImageProcessor imageProcessor;

    public void create(FileStorage fileStorage, TempFile tempImageFile) {

        String ext = FilenameUtils.getExtension(tempImageFile.getFile().getName());
        if (!SUPPORTED_EXTENSIONS.contains(ext)) {
            throw new ThumbnailCreationException("썸네일을 만들기를 지원하지 않는 이미지 확장자입니다.");
        }

        log.debug("썸네일 이미지를 만들었습니다 file: '{}'",tempImageFile.getFile().getName());

        try {
            String sourceFilePath = tempImageFile.getFile().getAbsolutePath();
            if (!sourceFilePath.endsWith("." + ext)) {
                throw new IllegalAccessException("이미지 파일 확장자 아닙니다.");
            }
            String tempThumbnailFilePath = ImageUtils.getThumbnailVersion(tempImageFile.getFile().getAbsolutePath());
            Size resizeTo = getTargetSize(sourceFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Size getTargetSize(String imageFilePath) {
        Size actualSize = imageProcessor.getSize(imageFilePath);
    }
}
