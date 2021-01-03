package kr.co.iamdesigner.domain.application;

import kr.co.iamdesigner.domain.application.commands.ProductRegisterCommand;
import kr.co.iamdesigner.domain.application.commands.ProductSpecieRegisterCommand;
import kr.co.iamdesigner.domain.model.product.ProductSpecie;
import kr.co.iamdesigner.domain.model.product.ProductRegistrationException;

public interface ProductService {

    ProductSpecie findById(Long id);
    ProductSpecie findByName(String name);

    void register(ProductRegisterCommand command) throws ProductRegistrationException;


}
