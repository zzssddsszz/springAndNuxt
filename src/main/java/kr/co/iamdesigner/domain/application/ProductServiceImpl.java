package kr.co.iamdesigner.domain.application;

import kr.co.iamdesigner.domain.application.commands.ProductRegisterCommand;
import kr.co.iamdesigner.domain.model.product.ProductRegistrationException;
import kr.co.iamdesigner.domain.model.product.ProductSpecie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    @Override
    public ProductSpecie findById(Long id) {
        return null;
    }

    @Override
    public ProductSpecie findByName(String name) {
        return null;
    }

    @Override
    public void register(ProductRegisterCommand command) throws ProductRegistrationException {

    }
}
