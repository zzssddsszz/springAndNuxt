package kr.co.iamdesigner.domain.model.tag;

import kr.co.iamdesigner.domain.model.product.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {
    @Id
    @Column(name = "TAG_ID")
    private Long id;

    @Column
    private String name;

    @Column
    @OneToMany(mappedBy = "PRODUCT_ID")
    private List<Product> productList = new ArrayList<>();
}
