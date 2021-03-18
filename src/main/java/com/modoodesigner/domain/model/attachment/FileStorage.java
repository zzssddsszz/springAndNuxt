package com.modoodesigner.domain.model.attachment;

import com.modoodesigner.domain.common.file.TempFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {

    TempFile saveAsTempFile(String folder, MultipartFile file);

    void saveTempFile(TempFile tempFile);

    String saveUploaded(String folder, MultipartFile file);
}
