package kr.co.iamdesigner.domain.model.part.pendant;

import kr.co.iamdesigner.domain.model.product.ProductSpecie;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SuppliedPendant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLIED_PENDANT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_SPECIE_ID")
    private ProductSpecie productSpecie;

    @OneToMany(mappedBy = "suppliedPendant")
    private List<Pendant> pendant = new ArrayList<>();

    private int amount;
}
