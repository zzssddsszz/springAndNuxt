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
@Table(name = "PRODUCT")
@Getter
@EqualsAndHashCode
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @OneToMany(mappedBy = "product")
    private List<SuppliedPart> suppliedPart = new ArrayList<>();

    private int price;

    private String mainImageAddress;

    private String subImageAddressList;

    private String name;

    private double rateDiscount;

    private int flatDiscount;

    @OneToMany(mappedBy = "product")
    private List<Tag> tagList = new ArrayList<>();

    @Lob
    private String detailedHtml;

}
