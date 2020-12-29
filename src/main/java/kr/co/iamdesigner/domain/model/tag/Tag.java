package kr.co.iamdesigner.domain.model.tag;

import kr.co.iamdesigner.domain.model.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TAG")
public class Tag {
    @Id
    @Column(name = "TAG_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
