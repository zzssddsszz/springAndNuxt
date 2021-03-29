package com.modoodesigner.domain.model.product;

import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.model.attachment.Attachment;
import com.modoodesigner.domain.model.attachment.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class ProductRegistrationManagement {
    private final ProductRepository repository;
    private final AttachmentRepository attachmentRepository;

    public Product register(ProductRegisterCommand command) {
        Product product = new Product(command);
        List<Long> mainImages = command.getMainImages();
        List<Long> contentImages = command.getContentImages();

        IntStream.range(0, mainImages.size())
                .forEach(index -> {
                    Attachment attachment = attachmentRepository.findById(mainImages.get(index)).orElseThrow();
                    attachment.setIndex(index);
                    product.addImage(attachment);
                });

        contentImages.forEach((id -> {
            Attachment attachment = attachmentRepository.findById(id).orElseThrow();
            if (attachment.isOrphan()){
                attachment.activation();
            }

        }));

        return repository.save(product);
    }
}
