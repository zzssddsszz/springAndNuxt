package com.modoodesigner.domain.model.product;

import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.common.model.BaseEntity;
import com.modoodesigner.domain.model.attachment.Attachment;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    private Long price;

    @OneToMany
    private List<Attachment> images;

    private String name;

    @Lob
    private String content;

    public Product(ProductRegisterCommand command) {
        name = command.getName();
        content = command.getContent();
    }

}
