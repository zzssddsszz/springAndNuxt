package kr.co.iamdesigner.domain.model.part;

import kr.co.iamdesigner.domain.model.product.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SuppliedPart {

    @Id
    Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private BasePart part;

    @Column
    private String name;

    @Column
    private int amount;
}
