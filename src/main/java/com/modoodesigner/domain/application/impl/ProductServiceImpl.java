package com.modoodesigner.domain.application.impl;

import com.modoodesigner.domain.application.ProductService;
import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.application.commands.ProductSearchCommand;
import com.modoodesigner.domain.model.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductManagement productRegistrationManagement;
    private final ProductRepository repository;

    @Override
    public Product findById(Long id) {
        Product product = repository.findById(id).orElseThrow();
        product.getImages();
        return product;
    }

    @Override
    public Product findByName(String name) {
        return null;
    }

    @Override
    public void register(ProductRegisterCommand command) throws ProductRegistrationException {
        productRegistrationManagement.register(command);
    }

    @Override
    public Page<Product> findAllWithFirstImage(ProductSearchCommand command) {
        Page<Product> allWithFirstImage = repository.findAllWithFirstImage(command);
        return allWithFirstImage;
    }

    @Override
    public Product getProductById(Long id) {
        return repository.getProductByIdWithImages(id);
    }

    @Override
    public void edit(ProductRegisterCommand command, Long id) {
        productRegistrationManagement.edit(command,id);
    }
}
