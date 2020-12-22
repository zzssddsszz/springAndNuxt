package kr.co.iamdesigner.domain.model.user;

import kr.co.iamdesigner.domain.common.security.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationManagement {

    private final UserRepository repository;
    private final PasswordEncryptor passwordEncryptor;

    public User register(String username, String emailAddress, String password) throws RegistrationException {
        if (repository.existsByUsername(username)) {
            throw new UsernameExistsException();
        }
        if (repository.existsByEmailAddress(emailAddress)) {
            throw new EmailAddressExistsException();
        }

        String encryptedPassword = passwordEncryptor.encrypt(password);
        User newUser = User.builder()
                .username(username)
                .emailAddress(emailAddress)
                .password(encryptedPassword)
                .build();
        repository.save(newUser);

        return newUser;

    }

}
