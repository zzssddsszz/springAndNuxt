package com.modoodesigner.domain.model.attachment;

import com.modoodesigner.domain.common.model.BaseEntity;
import com.modoodesigner.domain.model.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Attachment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String fileName;

    private String filePath;

    private String fileType;

    private boolean thumbnailCreated;


    private boolean orphan;

    @Builder
    public Attachment( User user, String fileName, String filePath, boolean thumbnailCreated) {
        this.user = user;
        this.fileName = fileName;
        this.fileType = FilenameUtils.getExtension(fileName);
        this.filePath = filePath;
        this.thumbnailCreated = thumbnailCreated;
        this.orphan = true;
    }
}
