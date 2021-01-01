package kr.co.iamdesigner.domain.model.part.pendant;

import kr.co.iamdesigner.factory.RegisterCommandFactory;
import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.PartExistsException;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class PendantRegistrationManagementTest {
    @Autowired PendantRegistrationManagement management;

    @Test
    void pendantRegistration_ExistTest_shouldFail() throws PartRegistrationException {
        PendantRegisterCommand command = RegisterCommandFactory.getPendantCommand();
        management.register(command);
        assertThrows(PartExistsException.class, () -> management.register(command));
    }

    @Test
    void pendantRegistration_differentOnlyType_shouldSuccess() throws PartRegistrationException {
        PendantRegisterCommand command = RegisterCommandFactory.getPendantCommand();
        Pendant pendant1 = management.register(command);
        command.setMountingType(MountingType.DOUBLE);
        Pendant pendant2 = management.register(command);
        assertNotEquals(pendant1,pendant2);
    }

    @Test
    void pendantRegistration_uniqueEntity_shouldSuccess() throws PartRegistrationException {
        PendantRegisterCommand command = RegisterCommandFactory.getPendantCommand();
        management.register(command);
    }
}