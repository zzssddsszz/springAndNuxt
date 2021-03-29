package com.modoodesigner.domain.model.product;

import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.model.attachment.Attachment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductRegistrationManagement {
    private final ProductRepository repository;

    public Product register(ProductRegisterCommand command) {
        Product product = new Product(command);

        Long[] imageIndex = command.getMainImageIndex();
        List<Attachment> images = product.getImages();
        Arrays.stream(imageIndex).toArray(value -> images.add(repository.findAllById(value)))


        return repository.save(product);
    }
}
