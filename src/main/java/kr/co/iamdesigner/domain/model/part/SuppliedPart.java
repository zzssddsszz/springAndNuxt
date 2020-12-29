package kr.co.iamdesigner.domain.model.part;

import kr.co.iamdesigner.domain.model.part.BasePart;
import kr.co.iamdesigner.domain.model.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "SUPPLIED_PART" )
public class SuppliedPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLIED_PART_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "BASE_PART_ID")
    private BasePart basePart;

    private String name;

    private int amount;
}
