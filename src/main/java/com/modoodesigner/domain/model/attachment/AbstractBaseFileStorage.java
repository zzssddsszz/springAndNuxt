package com.modoodesigner.domain.model.attachment;

import com.modoodesigner.domain.common.file.TempFile;
import org.springframework.web.multipart.MultipartFile;

public class AbstractBaseFileStorage implements FileStorage {
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
