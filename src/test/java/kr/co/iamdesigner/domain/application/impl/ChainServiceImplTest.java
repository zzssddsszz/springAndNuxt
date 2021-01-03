package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.application.commands.ChainRegisterCommand;
import kr.co.iamdesigner.domain.model.part.chain.Chain;
import kr.co.iamdesigner.domain.model.part.chain.ChainRegistrationManagement;
import kr.co.iamdesigner.domain.model.part.chain.ChainRepository;
import kr.co.iamdesigner.domain.model.part.common.PartExistsException;
import kr.co.iamdesigner.domain.model.part.common.PartRegistrationException;
import kr.co.iamdesigner.factory.RegisterCommandFactory;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class ChainServiceImplTest {
    private static ChainRegistrationManagement chainRegistrationManagementMock;
    private static ChainRepository repositoryMock;
    private static ChainServiceImpl instance;

    @BeforeAll
    static void setUp() {
        chainRegistrationManagementMock = mock(ChainRegistrationManagement.class);
        repositoryMock = mock(ChainRepository.class);
        instance = new ChainServiceImpl(chainRegistrationManagementMock, repositoryMock);
    }

    @SneakyThrows
    @Test
    void register_existName_shouldFail() {
        ChainRegisterCommand command = RegisterCommandFactory.getChainCommand();
        Chain chain = new Chain(command);
        doThrow(PartExistsException.class).when(chainRegistrationManagementMock).register(command);
        assertThrows(PartRegistrationException.class, () -> instance.register(command));
    }

}