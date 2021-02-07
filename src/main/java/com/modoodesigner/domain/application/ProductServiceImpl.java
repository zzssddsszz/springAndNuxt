package com.modoodesigner.domain.application;

import com.modoodesigner.domain.model.part.chain.ChainJoint;
import com.modoodesigner.domain.model.part.pendant.PendantJoint;
import com.modoodesigner.domain.model.product.*;
import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.model.part.chain.Chain;
import com.modoodesigner.domain.model.part.pendant.Pendant;
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
