package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.application.UserService;
import kr.co.iamdesigner.domain.application.commands.RegisterCommand;
import kr.co.iamdesigner.domain.common.event.DomainEventPublisher;
import kr.co.iamdesigner.domain.common.mail.MailManager;
import kr.co.iamdesigner.domain.common.mail.MessageVariable;
import kr.co.iamdesigner.domain.model.user.*;
import kr.co.iamdesigner.domain.model.user.events.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RegistrationManagement registrationManagement;
    private final DomainEventPublisher domainEventPublisher;
    private final MailManager mailManager;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("유저를 찾을수 없습니다.");
        }
        User user;
        if (username.contains("@")) {
            user = userRepository.findByEmailAddress(username);
        } else {
            user = userRepository.findByUsername(username);
        }
        if (user == null) {
            throw new UsernameNotFoundException(username + "의 유저를 찾을 수 없습니다.");
        }
        return new SimpleUser(user);
    }

    @Override
    public User findById(UserId userId) {
        return userRepository.findById(userId.value()).orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
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
}
