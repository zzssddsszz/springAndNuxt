package com.modoodesigner.domain.model.attachment;

import com.modoodesigner.domain.common.file.FileStorage;
import com.modoodesigner.domain.common.file.TempFile;
import com.modoodesigner.utils.ImageUtils;
import com.modoodesigner.utils.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.im4java.core.IM4JavaException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        Assert.isTrue(tempImageFile.getFile().exists(),"이미지 파일 '"+  tempImageFile.getFile().getAbsolutePath() + "' 를 찾을 수 없습니다.");

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
            imageProcessor.resize(sourceFilePath, tempThumbnailFilePath, resizeTo);

            fileStorage.saveTempFile(TempFile.create(tempImageFile.getRootTempPath(), Paths.get(tempThumbnailFilePath)));
            // 썸네일 파일 삭제
            Files.delete(Paths.get(tempThumbnailFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Size getTargetSize(String imageFilePath) throws IOException {
        Size actualSize = imageProcessor.getSize(imageFilePath);
        if (actualSize.getWidth() <= MAX_WIDTH && actualSize.getHeight() <= MAX_HEIGHT) {
            return actualSize;
        }

        if (actualSize.getWidth() > actualSize.getHeight()) {
            int width = MAX_WIDTH;
            int height = (int) Math.floor(((double) width / (double) actualSize.getWidth()) * actualSize.getHeight());
            return new Size(width, height);
        }else {
            int height = MAX_HEIGHT;
            int width = (int) Math.floor(((double) height / (double) actualSize.getHeight()) * actualSize.getWidth());
            return new Size(width, height);
        }
    }
}
