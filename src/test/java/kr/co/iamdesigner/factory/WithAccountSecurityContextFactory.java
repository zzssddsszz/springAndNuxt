package kr.co.iamdesigner.factory;

import kr.co.iamdesigner.domain.application.commands.UserRegisterCommand;
import kr.co.iamdesigner.domain.application.impl.UserServiceImpl;
import kr.co.iamdesigner.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import javax.transaction.Transactional;
import java.util.Collections;

@RequiredArgsConstructor
@Slf4j
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithUser> {

    private final UserServiceImpl userService;

    @SneakyThrows
    @Override
    public SecurityContext createSecurityContext(WithUser withUser) {

        UserDetails userDetails = userService.loadUserByUsername(withUser.value());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        return context;
    }
}
