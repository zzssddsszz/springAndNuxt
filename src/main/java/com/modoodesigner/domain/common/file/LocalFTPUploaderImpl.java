package com.modoodesigner.domain.common.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"local","test"})
@Slf4j
public class LocalFTPUploaderImpl implements FTPUploader {
    @Override
    public void upload(TempFile tempImageFile) {
        log.info(tempImageFile.getFile().getName()+"이 FTP 로 업로드 되었습니다.");
    }
}
