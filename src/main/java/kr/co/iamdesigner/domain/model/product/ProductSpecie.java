package kr.co.iamdesigner.domain.model.product;

import kr.co.iamdesigner.domain.common.model.BaseEntity;
import kr.co.iamdesigner.domain.model.part.chain.SuppliedChain;
import kr.co.iamdesigner.domain.model.part.pendant.SuppliedPendant;
import kr.co.iamdesigner.domain.model.tag.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ProductSpecie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_SPECIE_ID")
    private Long id;

    @OneToMany(mappedBy = "productSpecie")
    private List<SuppliedPendant> suppliedPendants = new ArrayList<>();

    @OneToMany(mappedBy = "productSpecie")
    private List<SuppliedChain> suppliedChains = new ArrayList<>();

    @OneToMany(mappedBy = "productSpecie")
    private List<Tag> tagList = new ArrayList<>();



}
