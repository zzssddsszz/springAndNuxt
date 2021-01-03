package kr.co.iamdesigner.domain.model.product;

import kr.co.iamdesigner.domain.application.commands.ProductRegisterCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRegistrationManagement {
    private final ProductRepository repository;

    public Product register(ProductRegisterCommand command){
        Product product = new Product(command);

        return repository.save(product);
    }
}
