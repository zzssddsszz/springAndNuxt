package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.model.part.chain.Chain;
import kr.co.iamdesigner.domain.model.part.chain.SuppliedChain;
import kr.co.iamdesigner.domain.model.part.pendant.Pendant;
import kr.co.iamdesigner.domain.model.part.pendant.SuppliedPendant;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecieRegisterCommand {
    List<SuppliedChain> chainList = new ArrayList<>();
    List<SuppliedPendant> pendantList = new ArrayList<>();
}
