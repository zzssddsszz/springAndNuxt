package com.modoodesigner.domain.model.paper;

import com.modoodesigner.domain.common.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PaperText extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("PAPER_TEXT_ID")
    private Long id;

    @ManyToOne
    private Paper paperId;

    private boolean encrypt;

    @Lob
    private String image;

    @Lob
    private String encryptImage;

    @Lob
    private String object;

    @Lob
    private String encryptObject;

    private Long count;

}
