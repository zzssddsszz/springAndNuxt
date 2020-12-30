package kr.co.iamdesigner.domain.model.part.pendant;

import kr.co.iamdesigner.domain.model.part.pendant.Pendant;
import kr.co.iamdesigner.domain.model.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "SUPPLIED_PART")
public class SuppliedPendant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLIED_PART_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "PENDANT_ID")
    private Pendant pendant;

    private String name;

    private int amount;
}
