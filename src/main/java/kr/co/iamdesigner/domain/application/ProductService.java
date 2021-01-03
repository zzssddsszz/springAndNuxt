package kr.co.iamdesigner.domain.application;

import kr.co.iamdesigner.domain.application.commands.ProductRegisterCommand;
import kr.co.iamdesigner.domain.model.product.Product;
import kr.co.iamdesigner.domain.model.product.ProductRegistrationException;

public interface ProductService {

    Product findById(Long id);
    Product findByName(String name);

    void register(ProductRegisterCommand command) throws ProductRegistrationException;

}
