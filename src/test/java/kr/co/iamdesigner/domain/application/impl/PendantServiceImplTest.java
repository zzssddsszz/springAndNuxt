/*
package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.*;
import kr.co.iamdesigner.domain.model.part.common.PartExistsException;
import kr.co.iamdesigner.domain.model.part.pendant.PartRegistrationManagement;
import kr.co.iamdesigner.domain.model.part.pendant.PendantRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class PendantServiceImplTest {
    private static PartRegistrationManagement partRegistrationManagementMock;
    private static PendantRepository pendantRepositoryMock;
    private static PendantServiceImpl instance;

    @BeforeAll
    static void setUp(){
        partRegistrationManagementMock = mock(PartRegistrationManagement.class);
        pendantRepositoryMock = mock(PendantRepository.class);
        instance = new PendantServiceImpl(partRegistrationManagementMock, pendantRepositoryMock);
    }

    @Test
    void register_nullCommand_shouldFail(){
        assertThrows(NullPointerException.class, () -> instance.register(null));
    }

    @Test
    void register_existName_shouldFail() throws PartRegistrationException {
        PendantRegisterCommand command = PendantRegisterCommand.builder()
                .name("existing")
                .buyPrice(5000)
                .stock(10)
                .partType(PartType.PENDANT)
                .color(PlatingColor.NONE)
                .build();

        doThrow(PartExistsException.class).when(partRegistrationManagementMock)
                .register(command);




    }
}*/
