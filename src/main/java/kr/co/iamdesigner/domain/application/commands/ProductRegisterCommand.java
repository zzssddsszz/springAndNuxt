package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.model.part.chain.Chain;
import kr.co.iamdesigner.domain.model.part.chain.ChainJoint;
import kr.co.iamdesigner.domain.model.part.chain.SuppliedChain;
import kr.co.iamdesigner.domain.model.part.pendant.Pendant;
import kr.co.iamdesigner.domain.model.part.pendant.PendantJoint;
import kr.co.iamdesigner.domain.model.part.pendant.SuppliedPendant;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ProductRegisterCommand {
    private String name;
    private String chainList;

}
