package kr.co.iamdesigner.domain.model.part.pendant;

import kr.co.iamdesigner.domain.model.part.BasePart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Pendant extends BasePart {
    @Column
    @Enumerated(EnumType.STRING)
    MountingType mountingType;
}
