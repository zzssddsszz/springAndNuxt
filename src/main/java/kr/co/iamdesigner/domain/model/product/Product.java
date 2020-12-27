package kr.co.iamdesigner.domain.model.product;

import kr.co.iamdesigner.domain.model.part.SuppliedPart;
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
    @Column
    private Long id;

    @Column
    @OneToMany(mappedBy = "product")
    private List<SuppliedPart> suppliedPartList = new ArrayList<>();

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
    private String tagList;

    @Column
    private String detailedHtml;

}
