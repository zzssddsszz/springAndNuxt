package com.modoodesigner.domain.model.product;

import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.common.model.BaseEntity;
import com.modoodesigner.domain.model.attachment.Attachment;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Slf4j
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_id")
    @OrderColumn(name = "position")
    private List<Attachment> images = new ArrayList<>();

    private String name;

    @Lob
    private String content;

    public Product(ProductRegisterCommand command) {
        name = command.getName();
        content = command.getContent();
    }

    public void addImage(Attachment image) {
        images.add(image);
        if (image.isOrphan()){
            image.activation();
            log.debug(image.getId()+" 의 이미지가 활성화 되었습니다.");
        }
    }
}
