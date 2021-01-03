package kr.co.iamdesigner.factory;

import kr.co.iamdesigner.domain.application.commands.UserRegisterCommand;
import kr.co.iamdesigner.domain.application.impl.UserServiceImpl;
import kr.co.iamdesigner.domain.model.user.SimpleUser;
import kr.co.iamdesigner.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithUser> {


    @SneakyThrows
    @Override
    public SecurityContext createSecurityContext(WithUser withUser) {

        String name = withUser.value();
        User user = User.create(name,name+"@test.com","MyPassword!@");
        FieldUtils.writeField(user,"id",1L,true);
        SimpleUser simpleUser = new SimpleUser(user);
        List<GrantedAuthority> role = (List<GrantedAuthority>) simpleUser.getAuthorities();
        if (name.equals("admin")){
            role.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(simpleUser, simpleUser.getPassword(), role);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        return context;
    }
}
