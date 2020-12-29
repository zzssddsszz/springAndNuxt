package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.application.commands.BasePartRegisterCommand;
import kr.co.iamdesigner.domain.model.part.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class PartServiceImplTest {
    private static PartRegistrationManagement partRegistrationManagementMock;
    private static PartRepository partRepositoryMock;
    private static PartServiceImpl instance;

    @BeforeAll
    static void setUp(){
        partRegistrationManagementMock = mock(PartRegistrationManagement.class);
        partRepositoryMock = mock(PartRepository.class);
        instance = new PartServiceImpl(partRegistrationManagementMock,partRepositoryMock);
    }

    @Test
    void register_nullCommand_shouldFail(){
        assertThrows(NullPointerException.class, () -> instance.register(null));
    }

    @Test
    void register_existName_shouldFail() throws PartRegistrationException {
        BasePartRegisterCommand command = BasePartRegisterCommand.builder()
                .name("existing")
                .buyPrice(5000)
                .stock(10)
                .partType(PartType.PENDANT)
                .color(PlatingColor.NONE)
                .build();

        doThrow(PartExistsException.class).when(partRegistrationManagementMock)
                .register(command);




    }
}