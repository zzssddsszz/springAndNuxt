package com.modoodesigner.domain.application.impl;

import com.modoodesigner.domain.application.AttachmentService;
import com.modoodesigner.domain.application.commands.AttachmentUploadCommand;
import com.modoodesigner.domain.model.attachment.Attachment;
import com.modoodesigner.domain.model.attachment.AttachmentManagement;
import com.modoodesigner.domain.model.attachment.AttachmentRepository;
import com.modoodesigner.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentManagement attachmentManagement;
    private final AttachmentRepository repository;


    @Override

    public Attachment findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Attachment upload(AttachmentUploadCommand command) {
        return attachmentManagement.save(command.getUserId().value(), command.getFile());
    }

    public Attachment imageUpload(AttachmentUploadCommand command) throws IllegalAccessException {

        if (!ImageUtils.isImage(command.getFile().getContentType())){
            throw new IllegalAccessException("이미지 파일이 아닙니다.");
        }
        return attachmentManagement.save(command.getUserId().value(), command.getFile());
    }
}
