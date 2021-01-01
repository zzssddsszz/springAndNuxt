package kr.co.iamdesigner.domain.model.part.pendant;

import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.Material;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import kr.co.iamdesigner.domain.model.part.common.PlatingColor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PendantRegistrationManagementTest {
    @Autowired PendantRegistrationManagement management;

    @Test
    void pendantRegistration_ExistTest_shouldFail() throws PartRegistrationException {
        PendantRegisterCommand command = PendantRegisterCommand.builder()
                .name("팬던트이름")
                .buyPrice(1000)
                .material(Material.SILVER)
                .color(PlatingColor.BLACK)
                .mountingType(MountingType.SINGLE)
                .stock(20)
                .build();
        management.register(command);
        assertThrows(DataIntegrityViolationException.class, () -> management.register(command));
    }

    @Test
    void pendantRegistration_differentOnlyType_shouldSuccess() throws PartRegistrationException {
        PendantRegisterCommand command = PendantRegisterCommand.builder()
                .name("팬던트이름")
                .buyPrice(1000)
                .material(Material.SILVER)
                .color(PlatingColor.BLACK)
                .mountingType(MountingType.SINGLE)
                .stock(20)
                .build();
        Pendant pendant1 = management.register(command);
        command.setMountingType(MountingType.DOUBLE);
        Pendant pendant2 = management.register(command);
        assertNotEquals(pendant1,pendant2);
    }

    @Test
    void pendantRegistration_uniqueEntity_shouldSuccess() throws PartRegistrationException {
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
}