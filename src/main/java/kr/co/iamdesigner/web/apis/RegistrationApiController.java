package kr.co.iamdesigner.web.apis;

import kr.co.iamdesigner.domain.application.UserService;
import kr.co.iamdesigner.domain.application.commands.RegistrationCommand;
import kr.co.iamdesigner.web.payload.RegistrationPayload;
import kr.co.iamdesigner.web.results.ApiResult;
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
            RegistrationCommand command = payload.toCommand();
            addTriggeredBy(command, request);

            service.register(command);
            return Result.created();
        }

        return null;
    }
}
