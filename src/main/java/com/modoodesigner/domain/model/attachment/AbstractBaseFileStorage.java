package com.modoodesigner.domain.model.attachment;

import com.modoodesigner.domain.common.file.FileStorageException;
import com.modoodesigner.domain.common.file.TempFile;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

public abstract class AbstractBaseFileStorage implements FileStorage {

    protected TempFile saveMultipartFileToLocalTempFolder(String rootTempPath, String folder, MultipartFile multipartFile) {

        Path storagePath = Paths.get(rootTempPath, folder).toAbsolutePath().normalize();
        try {
            Files.createDirectories(storagePath);
        } catch (IOException e) {
            throw new FileStorageException("업로드 폴더 생성에 실패했습니다.",e);
        }

        String finalFileName = generateFileName(multipartFile);
        Path targetLocation = storagePath.resolve(finalFileName);

        //TODO 여기
    }

    protected String generateFileName(MultipartFile multipartFile){
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if (fileName.contains("..")) {
            throw new FileStorageException("잘못된 파일 이름 '" + fileName + "'");
        }

        String timestamp = String.valueOf(new Date().getTime());
        String uuid = UUID.randomUUID().toString();
        String ext = FilenameUtils.getExtension(fileName);
        return timestamp + "." + uuid + (StringUtils.hasText(ext) ? ("." + ext) : "");
    }

}
