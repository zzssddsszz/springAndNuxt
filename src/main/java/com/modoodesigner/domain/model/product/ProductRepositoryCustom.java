package com.modoodesigner.domain.model.product;

import com.modoodesigner.domain.application.commands.ProductSearchCommand;
import org.springframework.data.domain.Page;

public interface ProductRepositoryCustom {

    Product getProductByIdWithImages(Long id);
    Page<ProductDTO> findAllWithFirstImage(ProductSearchCommand command);
}
