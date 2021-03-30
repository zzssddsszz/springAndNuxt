package com.modoodesigner.domain.application.impl;

import com.modoodesigner.domain.application.ProductService;
import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.model.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return repository.findById(id).orElseThrow();
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
    public Page<Product> findByAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void edit(ProductRegisterCommand command, Long id) {
        productRegistrationManagement.edit(command,id);
    }
}
