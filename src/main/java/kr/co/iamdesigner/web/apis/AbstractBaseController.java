package kr.co.iamdesigner.web.apis;

import kr.co.iamdesigner.domain.application.commands.AnonymousCommand;
import kr.co.iamdesigner.domain.application.commands.UserCommand;
import kr.co.iamdesigner.domain.model.user.SimpleUser;
import kr.co.iamdesigner.utils.RequestUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBaseController {

    static void addTriggeredBy(UserCommand command, HttpServletRequest request) {
        Assert.notNull(request.getUserPrincipal(), "UserPrincipal이 요청에 있어야 합니다.");
        UsernamePasswordAuthenticationToken userPrincipal = (UsernamePasswordAuthenticationToken) request.getUserPrincipal();
        SimpleUser currentUser = (SimpleUser) userPrincipal.getPrincipal();
        command.triggeredBy(currentUser.getUserId(), RequestUtils.getIpAddress(request));
    }

    void addTriggeredBy(AnonymousCommand command, HttpServletRequest request) {
        command.triggeredBy(RequestUtils.getIpAddress(request));
    }

}
