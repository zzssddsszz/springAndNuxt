package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.application.commands.RegisterCommand;
import kr.co.iamdesigner.domain.common.event.DomainEventPublisher;
import kr.co.iamdesigner.domain.common.mail.MailManager;
import kr.co.iamdesigner.domain.common.mail.MessageVariable;
import kr.co.iamdesigner.domain.model.user.*;
import kr.co.iamdesigner.domain.model.user.events.UserRegisteredEvent;
import kr.co.iamdesigner.utils.IpAddress;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private static RegistrationManagement registrationManagementMock;
    private static DomainEventPublisher domainEventPublisherMock;
    private static MailManager mailManagerMock;
    private static UserRepository userRepositoryMock;
    private static UserServiceImpl instance;

    @BeforeAll
    static void setUp(){
        registrationManagementMock = mock(RegistrationManagement.class);
        domainEventPublisherMock = mock(DomainEventPublisher.class);
        mailManagerMock = mock(MailManager.class);
        userRepositoryMock = mock(UserRepository.class);
        instance = new UserServiceImpl(registrationManagementMock, mailManagerMock, userRepositoryMock);
    }

    @Test
    void loadUserByUsername_emptyUsername_shouldFail(){
        Exception exception = null;
        try {
            instance.loadUserByUsername("");
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception instanceof UsernameNotFoundException);
        verify(userRepositoryMock, never()).findByUsername("");
        verify(userRepositoryMock, never()).findByEmailAddress("");
    }

    @Test
    void loadUserByUsername_existUsername_shouldSucceed() throws IllegalAccessException {
        String existUsername = "ExistUsername";
        User foundUser = User.create(existUsername, "test@test.com", "EncryptedPassword!");
        FieldUtils.writeField(foundUser,"id",1L,true);
        when(userRepositoryMock.findByUsername(existUsername)).thenReturn(foundUser);
        Exception exception = null;
        UserDetails userDetails = null;
        try {
            userDetails = instance.loadUserByUsername(existUsername);
        } catch (Exception e) {
            exception = e;
        }
        assertNull(exception);
        verify(userRepositoryMock).findByUsername(existUsername);
        verify(userRepositoryMock,never()).findByEmailAddress(existUsername);
        assertNotNull(userDetails);
        assertEquals(existUsername, userDetails.getUsername());
        assertTrue(userDetails instanceof SimpleUser);
    }

    @Test
    void register_nullCommand_shouldFail(){
        assertThrows(NullPointerException.class, () -> instance.register(null));
    }

    @Test
    void register_existingUsername_shouldFail() throws RegistrationException {
        String username = "existing";
        String emailAddress = "test@test.com";
        String password = "MyPassword!@";
        doThrow(UsernameExistsException.class).when(registrationManagementMock)
                .register(username, emailAddress, password);
        RegisterCommand command = new RegisterCommand(username, emailAddress, password);
        assertThrows(RegistrationException.class,()->instance.register(command));
    }

    @Test
    void register_validCommand_shouldSucceed() throws RegistrationException {
        String username = "existing";
        String emailAddress = "test@test.com";
        String password = "MyPassword!@";
        User newUser = mock(User.class);
        when(newUser.getUserId()).thenReturn(new UserId(1));
        when(newUser.getUsername()).thenReturn(username);
        when(newUser.getEmailAddress()).thenReturn(emailAddress);
        when(newUser.getPassword()).thenReturn(password);

        when(registrationManagementMock.register(username, emailAddress, password)).thenReturn(newUser);

        IpAddress ipAddress = new IpAddress("127.0.0.1");
        RegisterCommand command = mock(RegisterCommand.class);
        when(command.getUsername()).thenReturn(username);
        when(command.getEmailAddress()).thenReturn(emailAddress);
        when(command.getPassword()).thenReturn(password);
        when(command.getIpAddress()).thenReturn(ipAddress);

        instance.register(command);

        verify(mailManagerMock).send(
                emailAddress,
                "나는디자이너에 가입해주셔서 감사합니다.",
                "welcome.ftl",
                MessageVariable.from("user",newUser)

        );

//        ArgumentCaptor<UserRegisteredEvent> argumentCaptor = ArgumentCaptor.forClass(UserRegisteredEvent.class);
//        verify(domainEventPublisherMock).publish(argumentCaptor.capture());
//
//        UserRegisteredEvent event = argumentCaptor.getValue();
//        assertEquals(newUser.getUserId(),event.getUserId());
//        assertEquals(ipAddress,event.getIpAddress());
    }




}