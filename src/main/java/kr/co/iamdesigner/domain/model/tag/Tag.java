package kr.co.iamdesigner.domain.model.tag;

import kr.co.iamdesigner.domain.common.model.BaseEntity;
import kr.co.iamdesigner.domain.model.product.ProductSpecie;

import javax.persistence.*;

@Entity
@Table(name = "TAG")
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_SPECIE_ID")
    private ProductSpecie productSpecie;
}
