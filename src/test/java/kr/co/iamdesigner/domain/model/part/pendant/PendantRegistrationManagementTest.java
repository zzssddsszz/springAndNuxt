package kr.co.iamdesigner.domain.model.part.pendant;

import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.Material;
import kr.co.iamdesigner.domain.model.part.common.PartExistsException;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import kr.co.iamdesigner.domain.model.part.common.PlatingColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PendantRegistrationManagementTest {
    @Autowired PendantRegistrationManagement management;

    @Test
    void pendantRegistrationTest_success() throws PartRegistrationException {
        PendantRegisterCommand command = PendantRegisterCommand.builder()
                .name("팬던트이름")
                .buyPrice(1000)
                .material(Material.SILVER)
                .color(PlatingColor.BLACK)
                .mountingType(MountingType.SINGLE)
                .stock(20)
                .build();
        management.register(command);
    }

    @Test
    void pendantRegistrationExistTest_shouldFail() throws PartRegistrationException {
        PendantRegisterCommand command = PendantRegisterCommand.builder()
                .name("팬던트이름")
                .buyPrice(1000)
                .material(Material.SILVER)
                .color(PlatingColor.BLACK)
                .mountingType(MountingType.SINGLE)
                .stock(20)
                .build();
        management.register(command);
        assertThrows(PartExistsException.class, () -> management.register(command));
    }
}