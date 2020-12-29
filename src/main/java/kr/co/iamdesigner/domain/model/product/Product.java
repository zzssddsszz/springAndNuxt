package kr.co.iamdesigner.domain.model.product;

import kr.co.iamdesigner.domain.model.part.SuppliedPart;
import kr.co.iamdesigner.domain.model.tag.Tag;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

//    @OneToMany(mappedBy = "product")
//    @JoinColumn(name = "SUPPLIED_PART_ID")
//    private List<SuppliedPart> suppliedPart = new ArrayList<>();

    private int price;

    private String mainImageAddress;

    private String subImageAddressList;

    private String name;

    private double rateDiscount;

    private int flatDiscount;

//    @OneToMany(mappedBy = "TAG_ID")
//    private List<Tag> tagList = new ArrayList<>();

    @Lob
    private String detailedHtml;

}
