package kr.co.iamdesigner.domain.model.part.pendant;

import kr.co.iamdesigner.domain.model.part.BasePart;

import javax.persistence.*;

@Entity
@Table(name = "PENDANT")
public class Pendant extends BasePart {
    @Column
    @Enumerated(EnumType.STRING)
    MountingType mountingType;
}
