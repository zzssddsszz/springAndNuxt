package com.modoodesigner.domain.application;

import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.application.commands.ProductSearchCommand;
import com.modoodesigner.domain.model.product.Product;
import com.modoodesigner.domain.model.product.ProductRegistrationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

     Product findById(Long id);
     Product findByName(String name);

    void register(ProductRegisterCommand command) throws ProductRegistrationException;

    void edit(ProductRegisterCommand command, Long id);

    Page<Product> findAllWithFirstImage(ProductSearchCommand command);

    Product getProductById(Long id);
}
