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

    @Column
    @OneToMany
    @JoinColumn(name = "PRODUCT_ID")
    private List<SuppliedPart> suppliedPart = new ArrayList<>();

    @Column
    private int price;

    @Column
    private String mainImageAddress;

    @Column
    private String subImageAddressList;

    @Column
    private String name;

    @Column
    private double rateDiscount;

    @Column
    private int flatDiscount;

    @Column
    @OneToMany(mappedBy = "TAG_ID")
    private List<Tag> tagList = new ArrayList<>();

    @Column
    private String detailedHtml;

}
