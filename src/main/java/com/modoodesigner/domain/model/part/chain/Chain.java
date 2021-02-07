package com.modoodesigner.domain.model.part.chain;

import com.modoodesigner.domain.application.commands.ChainRegisterCommand;
import com.modoodesigner.domain.model.part.common.BasePart;
import com.modoodesigner.domain.model.part.common.PartCodeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
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
