package kr.co.iamdesigner.domain.model.part.chain;

import kr.co.iamdesigner.domain.application.commands.ChainRegisterCommand;
import kr.co.iamdesigner.domain.application.commands.PartRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.BasePart;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"code","color","material"}
                )
        }
)
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
    }
}
