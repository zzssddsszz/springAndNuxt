package kr.co.iamdesigner.domain.model.part.pendant;

import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.BasePart;
import kr.co.iamdesigner.domain.model.part.common.PartCodeUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Pendant extends BasePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PENDANT_ID")
    private Long id;

    @OneToMany(mappedBy = "pendant")
    private List<SuppliedPendant> suppliedPendant = new ArrayList<>();

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
