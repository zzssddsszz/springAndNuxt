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
public class PaperPendant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("PAPER_PENDANT_ID")
    private Long id;

    @ManyToOne
    private Paper paperId;

    @Lob
    private String image;

    @Lob
    private String object;

    private Long count;

}
