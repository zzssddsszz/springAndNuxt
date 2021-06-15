package com.modoodesigner.domain.model.paper;

import com.modoodesigner.domain.common.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Paper extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("PAPER_ID")
    private Long id;


    @OneToMany(mappedBy = "paperId")
    private List<PaperText> paperText;

    @OneToMany(mappedBy = "paperId")
    private List<PaperPendant> paperEngrave;

    @Lob
    private String image;

    @Lob
    private String object;

    private Long count;

}
