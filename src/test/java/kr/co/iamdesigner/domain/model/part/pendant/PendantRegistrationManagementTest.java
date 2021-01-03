package kr.co.iamdesigner.domain.model.part.pendant;

import kr.co.iamdesigner.factory.RegisterCommandFactory;
import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.PartExistsException;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PendantRegistrationManagementTest {

    @Mock
    PendantRepository repository;
    @InjectMocks
    PendantRegistrationManagement management;

    @Test
    void pendantRegistration_ExistTest_shouldFail() throws PartRegistrationException {
        PendantRegisterCommand command = RegisterCommandFactory.getPendantCommand();
        Pendant pendant = new Pendant(command);
        when(repository.existsByCode(pendant.getCode())).thenReturn(true);
        assertThrows(PartRegistrationException.class, () -> management.register(command));
    }

    @Test
    void pendantRegistration_uniqueEntity_shouldSuccess() throws PartRegistrationException {
        PendantRegisterCommand command = RegisterCommandFactory.getPendantCommand();
        Pendant pendant = new Pendant(command);

        when(repository.save(pendant)).thenReturn(new Pendant(command));

        Pendant registerPendant = management.register(command);

        assertNotNull(registerPendant.getCode());
    }
}