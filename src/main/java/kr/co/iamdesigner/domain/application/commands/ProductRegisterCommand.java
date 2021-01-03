package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.model.part.chain.SuppliedChain;
import kr.co.iamdesigner.domain.model.part.pendant.SuppliedPendant;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
public class ProductRegisterCommand {
    private String name;
    private List<SuppliedChain> chainList;
    private List<SuppliedPendant> pendantList;
}
