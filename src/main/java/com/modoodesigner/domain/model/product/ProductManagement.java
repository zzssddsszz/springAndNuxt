package com.modoodesigner.domain.model.product;

import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.model.attachment.Attachment;
import com.modoodesigner.domain.model.attachment.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class ProductManagement {
    private final ProductRepository repository;
    private final AttachmentRepository attachmentRepository;

    public void register(ProductRegisterCommand command) {
        Product product = new Product(command);
        imageActivate(command, product);
    }

    public void edit(ProductRegisterCommand command, Long id) {
        Product product = repository.findById(id).orElseThrow();
        product.setName(command.getName());
        product.setContent(command.getContent());
        product.setImages(new ArrayList<>());

        imageActivate(command, product);
    }

    private Product imageActivate(ProductRegisterCommand command, Product product) {
        List<Long> mainImages = command.getMainImages();

        if (!mainImages.isEmpty()) {
            IntStream.range(0, mainImages.size())
                    .forEach(index -> {
                        Attachment attachment = attachmentRepository.findById(mainImages.get(index)).orElseThrow();
                        attachment.setPosition(index);
                        product.addImage(attachment);
                    });
        }

        return repository.save(product);
    }
}
