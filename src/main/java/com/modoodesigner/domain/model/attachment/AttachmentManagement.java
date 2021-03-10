package com.modoodesigner.domain.model.attachment;

import com.modoodesigner.domain.common.file.FileStorageResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AttachmentManagement {

    private FileStorageResolver fileStorageResolver;

}
