package kr.co.iamdesigner.domain.model.part;

import kr.co.iamdesigner.domain.model.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
public class SuppliedPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLIED_PART_ID")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "PRODUCT_ID")
//    private Product product;

    @ManyToOne
    @JoinColumn(name = "BASE_PART_ID")
    private BasePart basePart;

    private String name;

    private int amount;
}
