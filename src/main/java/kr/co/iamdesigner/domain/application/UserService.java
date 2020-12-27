package kr.co.iamdesigner.domain.application;

import kr.co.iamdesigner.domain.application.commands.UserRegisterCommand;
import kr.co.iamdesigner.domain.model.user.UserRegistrationException;
import kr.co.iamdesigner.domain.model.user.User;
import kr.co.iamdesigner.domain.model.user.UserId;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findById(UserId userId);

    User findByUsername(String username);
    User findByEmailAddress(String emailAddress);

    /**
     *
     * @param command RegisterCommand 의 인스턴스다.
     * @throws UserRegistrationException 의 이유는 다음과 같다.
     *                                  1. Username 이 이미 존재하는경우
     *                                  2. 이메일이 이미 존재하는경우
     */
    void register(UserRegisterCommand command) throws UserRegistrationException;


}
