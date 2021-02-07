package com.modoodesigner.domain.model.part.pendant;

import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.model.part.common.BasePart;
import com.modoodesigner.domain.model.part.common.PartCodeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Pendant extends BasePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PENDANT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SUPPLIED_PENDANT_ID")
    private SuppliedPendant suppliedPendant;

    @Enumerated(EnumType.STRING)
    private MountingType mountingType;

    public Pendant(PendantRegisterCommand command) {
        super(command);
        mountingType = command.getMountingType();
        setAutoCode(this);
    }

    private void setAutoCode(BasePart basePart){
        PartCodeUtil.generate(basePart);
    }

}
