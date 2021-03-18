package com.modoodesigner.infrastructure.file.local;

import com.modoodesigner.domain.common.file.FileStorage;
import com.modoodesigner.domain.common.file.TempFile;
import com.modoodesigner.domain.model.attachment.AbstractBaseFileStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("localFileStorage")
public class LocalFileStorage extends AbstractBaseFileStorage {

    private String rootFolderPath;
    private String rootTempPath;

    public LocalFileStorage(@Value("${app.file-storage.local-root-folder}") String rootPath,
                            @Value("${app.file-storage.temp-folder}") String tempPath) {
        this.rootFolderPath = rootFolderPath;
        this.rootTempPath = rootTempPath;
    }

    @Override
    public TempFile saveAsTempFile(String folder, MultipartFile file) {
        return null;
    }

    @Override
    public void saveTempFile(TempFile tempFile) {

    }

    @Override
    public String saveUploaded(String folder, MultipartFile file) {
        return null;
    }
}
