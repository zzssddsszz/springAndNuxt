package kr.co.iamdesigner.web.apis;

import kr.co.iamdesigner.domain.application.UserService;
import kr.co.iamdesigner.domain.application.commands.RegisterCommand;
import kr.co.iamdesigner.domain.model.user.EmailAddressExistsException;
import kr.co.iamdesigner.domain.model.user.RegistrationException;
import kr.co.iamdesigner.domain.model.user.UsernameExistsException;
import kr.co.iamdesigner.web.payload.RegistrationPayload;
import kr.co.iamdesigner.web.results.ApiResult;
import kr.co.iamdesigner.web.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationApiController extends AbstractBaseController{
    private final UserService service;

    @PostMapping("/api/registrations")
    public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload, HttpServletRequest request) {

        try {
            RegisterCommand command = payload.toCommand();
            addTriggeredBy(command, request);

            service.register(command);
            return Result.created();
        } catch (RegistrationException e) {
            String errorMessage = "Registration failed";
            if(e instanceof UsernameExistsException){
                errorMessage = "유저이름이 이미 있습니다.";
            } else if (e instanceof EmailAddressExistsException) {
                errorMessage = "이메일이 이미 있습니다.";
            }
            return Result.failure(errorMessage);
        }
    }
}
