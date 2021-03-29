package com.modoodesigner.domain.application;

import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.model.product.Product;
import com.modoodesigner.domain.model.product.ProductSpecie;
import com.modoodesigner.domain.model.product.ProductRegistrationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductSpecie findById(Long id);
    ProductSpecie findByName(String name);

    void register(ProductRegisterCommand command) throws ProductRegistrationException;


    Page<Product> findByAll(Pageable pageable);
}
