package kr.co.iamdesigner.domain.model.part.chain;

import kr.co.iamdesigner.domain.model.product.Product;

import javax.persistence.*;

@Entity
public class SuppliedChain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLIED_CHAIN_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "CHAIN_ID")
    private Chain chain;

    private String name;

    private int amount;
}
