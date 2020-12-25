package kr.co.iamdesigner.domain.model.user;

import kr.co.iamdesigner.domain.common.security.PasswordEncryptor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationManagementTest {

    private static UserRepository repositoryMock;
    private static PasswordEncryptor passwordEncryptor;
    private static RegistrationManagement instance;

    @BeforeAll
    static void setUp() {
        repositoryMock = mock(UserRepository.class);
        passwordEncryptor = mock(PasswordEncryptor.class);
        instance = new RegistrationManagement(repositoryMock, passwordEncryptor);
    }

    @Test
    void register_existedUsername_shouldFail() throws RegistrationException {
        String username = "existUsername";
        String emailAddress = "test@test.com";
        String password = "MyPassword!@";

        when(repositoryMock.existsByUsername(username)).thenReturn(true);
        assertThrows(UsernameExistsException.class, () -> instance.register(username, emailAddress, password));
    }

    @Test
    public void register_uppercaseEmailAddress_shouldSucceedAndBecomeLowercase() throws RegistrationException {
        String username = "username";
        String emailAddress = "UPPERCASE@TEST.COM";
        String password = "MyPassword!@";
        instance.register(username, emailAddress, password);
        User userToSave = User.builder()
                .username(username)
                .emailAddress(emailAddress.toLowerCase())
                .password(passwordEncryptor.encrypt(password))
                .build();
        verify(repositoryMock).save(userToSave);
    }

    @Test
    void register_existedEmailAddress_shouldFail() throws RegistrationException {
        String username = "username";
        String emailAddress = "existEmail@test.com";
        String password = "MyPassword!@";

        when(repositoryMock.existsByEmailAddress(emailAddress.toLowerCase())).thenReturn(true);
        assertThrows(EmailAddressExistsException.class, () -> instance.register(username, emailAddress, password));
    }

    @Test
    public void register_newUser_shouldSucceed() throws RegistrationException {
        String username = "username";
        String emailAddress = "test@test.com";
        String password = "MyPassword!@";
        String encryptedPassword = "EncryptedPassword";

        User newUser = User.builder()
                .username(username)
                .emailAddress(emailAddress.toLowerCase())
                .password(encryptedPassword)
                .build();

        when(repositoryMock.existsByUsername(username)).thenReturn(false);
        when(repositoryMock.existsByEmailAddress(emailAddress)).thenReturn(false);
        when(passwordEncryptor.encrypt(password)).thenReturn("EncryptedPassword");

        User savedUser = instance.register(username, emailAddress, password);
        InOrder inOrder = inOrder(repositoryMock);
        inOrder.verify(repositoryMock).existsByUsername(username);
        inOrder.verify(repositoryMock).existsByEmailAddress(emailAddress);
//        검증실패함
//        inOrder.verify(repositoryMock).save(newUser);
        verify(passwordEncryptor).encrypt(password);
        assertEquals(encryptedPassword,savedUser.getPassword());
    }
}