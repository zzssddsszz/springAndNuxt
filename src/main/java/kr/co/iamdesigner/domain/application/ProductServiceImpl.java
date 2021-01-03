package kr.co.iamdesigner.domain.application;

import kr.co.iamdesigner.domain.application.commands.ProductRegisterCommand;
import kr.co.iamdesigner.domain.application.commands.ProductSpecieRegisterCommand;
import kr.co.iamdesigner.domain.model.part.chain.Chain;
import kr.co.iamdesigner.domain.model.part.chain.ChainJoint;
import kr.co.iamdesigner.domain.model.part.pendant.Pendant;
import kr.co.iamdesigner.domain.model.part.pendant.PendantJoint;
import kr.co.iamdesigner.domain.model.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRegistrationManagement productRegistrationManagement;
    private final ProductRepository productRepository;
    private final ProductSpecieRepository specieRepository;

    private Map<Chain, ChainJoint> chainList = new HashMap<Chain, ChainJoint>();
    private Map<Pendant, PendantJoint> pendantList = new HashMap<Pendant,PendantJoint>();


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
