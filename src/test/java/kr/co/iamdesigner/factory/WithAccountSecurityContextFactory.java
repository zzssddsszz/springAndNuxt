package kr.co.iamdesigner.factory;

import kr.co.iamdesigner.domain.application.commands.UserRegisterCommand;
import kr.co.iamdesigner.domain.application.impl.UserServiceImpl;
import kr.co.iamdesigner.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Collections;

@RequiredArgsConstructor
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithUser> {

    private final UserServiceImpl userService;

    @SneakyThrows
    @Override
    public SecurityContext createSecurityContext(WithUser withUser) {
        String name = withUser.value();
        UserRegisterCommand command = new UserRegisterCommand(name, name + "@test.com", "MyPassword!@");
        userService.register(command);
        User user = userService.findByUsername(name);
        if (withUser.admin()) {
            user.setAdmin(true);
        }
        UserDetails userDetails = userService.loadUserByUsername(name);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }
}
