package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import kr.co.iamdesigner.domain.model.part.pendant.Pendant;
import kr.co.iamdesigner.factory.RegisterCommandFactory;
import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.PartExistsException;
import kr.co.iamdesigner.domain.model.part.pendant.PendantRegistrationManagement;
import kr.co.iamdesigner.domain.model.part.pendant.PendantRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.parameters.P;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PendantServiceImplTest {
    @Mock
    private PendantRegistrationManagement pendantRegistrationManagementMock;
    @Mock
    static PendantRepository pendantRepositoryMock;
    @InjectMocks
    static PendantServiceImpl instance;

    @SneakyThrows
    @Test
    void register_existName_shouldFail() {
        PendantRegisterCommand command = RegisterCommandFactory.getPendantCommand();
        Pendant pendant = new Pendant(command);
        doThrow(PartExistsException.class).when(pendantRegistrationManagementMock)
                .register(command);
        Assertions.assertThrows(PartRegistrationException.class, () -> instance.register(command));
    }
}
