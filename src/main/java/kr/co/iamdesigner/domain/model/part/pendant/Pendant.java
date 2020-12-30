package kr.co.iamdesigner.domain.model.part.pendant;

import kr.co.iamdesigner.domain.application.commands.PartRegisterCommand;
import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.BasePart;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Pendant extends BasePart {

    @Id
    @Column(name = "PENDANT_ID")
    private Long id;

    @OneToMany(mappedBy = "pendant")
    private List<SuppliedPendant> suppliedPendant = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MountingType mountingType;

    public Pendant(PendantRegisterCommand command) {
        super(command);
        mountingType = command.getMountingType();
    }
}
