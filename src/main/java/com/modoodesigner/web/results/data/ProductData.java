package com.modoodesigner.web.results.data;

import com.modoodesigner.domain.model.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductData {
    private Long id;
    private String name;
    private String content;
    private List<AttachmentData> attachmentList;
    public ProductData(Product product) {
        id = product.getId();
        name = product.getName();
        content = product.getContent();
        attachmentList = product.getImages().stream()
                .map(img -> new AttachmentData(img)).collect(Collectors.toList());
    }
}
