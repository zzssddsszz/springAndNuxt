package kr.co.iamdesigner.domain.application.impl;

import kr.co.iamdesigner.domain.application.UserService;
import kr.co.iamdesigner.domain.application.commands.RegisterCommand;
import kr.co.iamdesigner.domain.model.user.RegistrationException;
import kr.co.iamdesigner.domain.model.user.RegistrationManagement;
import kr.co.iamdesigner.domain.model.user.User;
import kr.co.iamdesigner.domain.model.user.UserId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private RegistrationManagement registrationManagement;
    @Override
    public User findById(UserId userId) {
        return null;
    }

    @Override
    public void register(RegisterCommand command) throws RegistrationException {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
