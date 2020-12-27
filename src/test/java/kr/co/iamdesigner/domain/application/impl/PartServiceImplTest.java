package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.model.part.PartRegistrationException;
import kr.co.iamdesigner.domain.model.part.PartRegistrationManagement;
import kr.co.iamdesigner.domain.model.part.PartRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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
    void register_existName_shouldFail(){

    }
}