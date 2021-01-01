package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.factory.RegisterCommandFactory;
import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.PartExistsException;
import kr.co.iamdesigner.domain.model.part.pendant.PendantRegistrationManagement;
import kr.co.iamdesigner.domain.model.part.pendant.PendantRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class PendantServiceImplTest {
    private static PendantRegistrationManagement pendantRegistrationManagementMock;
    private static PendantRepository pendantRepositoryMock;
    private static PendantServiceImpl instance;

    @BeforeAll
    static void setUp(){
        pendantRegistrationManagementMock = mock(PendantRegistrationManagement.class);
        pendantRepositoryMock = mock(PendantRepository.class);
        instance = new PendantServiceImpl(pendantRegistrationManagementMock, pendantRepositoryMock);
    }

    @Test
    void register_nullCommand_shouldFail(){
        assertThrows(NullPointerException.class, () -> instance.register(null));
    }

    @SneakyThrows
    @Test
    void register_existName_shouldFail() {
        PendantRegisterCommand command = RegisterCommandFactory.getPendantCommand();

        doThrow(PartExistsException.class).when(pendantRegistrationManagementMock)
                .register(command);
    }
}
