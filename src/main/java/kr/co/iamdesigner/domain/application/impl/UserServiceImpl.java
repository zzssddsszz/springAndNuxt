package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.application.UserService;
import kr.co.iamdesigner.domain.application.commands.RegisterCommand;
import kr.co.iamdesigner.domain.common.event.DomainEventPublisher;
import kr.co.iamdesigner.domain.model.user.*;
import kr.co.iamdesigner.domain.model.user.events.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RegistrationManagement registrationManagement;
    private final DomainEventPublisher domainEventPublisher;
    private final MailManager mailManager;
    private final UserRepository userRepository;

    @Override
    public User findById(UserId userId) {
        return null;
    }

    @Override
    public void register(RegisterCommand command) throws RegistrationException {
        User newUser = registrationManagement.register(
                command.getUsername(),
                command.getEmailAddress(),
                command.getPassword());

        sendWelcomeMessage(newUser);
        domainEventPublisher.publish(new UserRegisteredEvent(newUser, command));

    }

    private void sendWelcomeMessage(User user) {
        mailManager.send(
                user.getEmailAddress(),
                "나는디자이너에 가입해주셔서 감사합니다.",
                "welcome.ftl",
                MessageVariable.from("user", user)
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
