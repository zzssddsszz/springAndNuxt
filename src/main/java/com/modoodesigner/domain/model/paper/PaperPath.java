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
public class PaperPath extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("PAPER_PATH_ID")
    private Long id;

    private String svg;

    @Lob
    private byte[] image;

    private Long count;

}
