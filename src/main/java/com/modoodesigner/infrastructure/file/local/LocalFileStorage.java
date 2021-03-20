package com.modoodesigner.infrastructure.file.local;

import com.modoodesigner.domain.common.file.FileStorageException;
import com.modoodesigner.domain.common.file.TempFile;
import com.modoodesigner.domain.common.file.AbstractBaseFileStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component("localFileStorage")
public class LocalFileStorage extends AbstractBaseFileStorage {

    private String rootFolderPath;
    private String rootTempPath;

    public LocalFileStorage(@Value("${app.file-storage.local-root-folder}") String rootPath,
                            @Value("${app.file-storage.temp-folder}") String tempPath) {
        this.rootFolderPath = rootPath;
        this.rootTempPath = tempPath;
    }

    @Override
    public TempFile saveAsTempFile(String folder, MultipartFile multipartFile) {
        return saveMultipartFileToLocalTempFolder(rootTempPath, folder, multipartFile);
    }

    @Override
    public void saveTempFile(TempFile tempFile) {
        Path targetLocation = Paths.get(rootFolderPath + "/" + tempFile.getFileRelativePath());
        try {
            Files.createDirectories(targetLocation);
            Files.copy(tempFile.getFile().toPath(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("임시파일 저장에 실패했습니다.", e);
        }
    }

    @Override
    public String saveUploaded(String folder, MultipartFile multipartFile) {
        TempFile locallySavedFile = saveMultipartFileToLocalTempFolder(rootTempPath, folder, multipartFile);
        return locallySavedFile.getFileRelativePath();
    }
}