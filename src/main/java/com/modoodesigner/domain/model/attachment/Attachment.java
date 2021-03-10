package com.modoodesigner.domain.model.attachment;

import com.modoodesigner.domain.common.model.BaseEntity;
import com.modoodesigner.domain.model.user.UserId;
import lombok.Builder;
import lombok.extern.java.Log;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Attachment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long userId;

    private String fileName;

    private String filePath;

    private String fileType;

    private boolean thumbnailCreated;

    private boolean archived;

    @Builder
    public Attachment(long userId, String fileName, String filePath, boolean thumbnailCreated) {
        this.userId = userId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.thumbnailCreated = thumbnailCreated;
    }
}
