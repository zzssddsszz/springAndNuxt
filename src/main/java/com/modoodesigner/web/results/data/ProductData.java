package com.modoodesigner.web.results.data;

import com.modoodesigner.domain.model.product.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductData {
    private Long id;
    private String name;
    private String content;
    public ProductData(Product product) {
        id = product.getId();
        name = product.getName();
        content = product.getContent();
    }
}
