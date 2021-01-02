package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.application.commands.ChainRegisterCommand;
import kr.co.iamdesigner.domain.model.part.chain.ChainRegistrationManagement;
import kr.co.iamdesigner.domain.model.part.chain.ChainRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ChainServiceImplTest {
    private static ChainRegistrationManagement chainRegistrationManagementMock;
    private static ChainRepository repositoryMock;
    private static ChainServiceImpl instance;

    @BeforeAll
    static void setUp(){
        chainRegistrationManagementMock = mock(ChainRegistrationManagement.class);
        repositoryMock = mock(ChainRepository.class);
        instance = new ChainServiceImpl(chainRegistrationManagementMock, repositoryMock);
    }

    @Test
    void register_nullCommand_shouldFail(){
        assertThrows(NullPointerException.class, () -> instance.register(null));
    }

    @Test
    void register_existName_shouldFail() {
        ChainRegisterCommand command;
    }

}