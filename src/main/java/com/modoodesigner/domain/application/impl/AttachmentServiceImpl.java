package com.modoodesigner.domain.application.impl;

import com.modoodesigner.domain.application.AttachmentService;
import com.modoodesigner.domain.application.commands.AttachmentUploadCommand;
import com.modoodesigner.domain.model.attachment.Attachment;
import com.modoodesigner.domain.model.attachment.AttachmentManagement;
import com.modoodesigner.domain.model.attachment.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        return attachmentManagement.save(command.getUserId(), command.getFile());
    }
}
