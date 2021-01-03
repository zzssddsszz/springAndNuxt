package kr.co.iamdesigner.domain.model.product;

import kr.co.iamdesigner.domain.application.commands.ProductRegisterCommand;
import kr.co.iamdesigner.domain.common.model.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    private int price;

    private String mainImageAddress;

    private String subImageAddressList;

    private String name;

    private double rateDiscount;

    private int flatDiscount;

    @Lob
    private String detailedHtml;

    public Product(ProductRegisterCommand command) {
        command.getName();
    }

}
