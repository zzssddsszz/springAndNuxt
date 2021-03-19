package com.modoodesigner.domain.model.attachment;

import com.modoodesigner.domain.common.model.BaseEntity;
import com.modoodesigner.domain.model.user.UserId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Attachment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UserId userId;

    private String fileName;

    private String filePath;

    private String fileType;

    private boolean thumbnailCreated;


    private boolean orphan;

    @Builder
    public Attachment( UserId userId, String fileName, String filePath, boolean thumbnailCreated) {
        this.userId = userId;
        this.fileName = fileName;
        this.fileType = FilenameUtils.getExtension(fileName);
        this.filePath = filePath;
        this.thumbnailCreated = thumbnailCreated;
        this.orphan = true;
    }
}
