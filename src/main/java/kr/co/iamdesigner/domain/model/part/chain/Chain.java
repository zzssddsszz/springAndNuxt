package kr.co.iamdesigner.domain.model.part.chain;

import kr.co.iamdesigner.domain.application.commands.ChainRegisterCommand;
import kr.co.iamdesigner.domain.application.commands.PartRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.BasePart;
import kr.co.iamdesigner.domain.model.part.common.PartCodeUtil;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Chain extends BasePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAIN_ID")
    private Long id;

    @OneToMany(mappedBy = "chain")
    private List<SuppliedChain> suppliedChain = new ArrayList<>();

    public Chain(ChainRegisterCommand command) {
        super(command);
        setAutoCode(this);
    }

    private void setAutoCode(BasePart basePart){
        PartCodeUtil.generate(basePart);
    }
}
