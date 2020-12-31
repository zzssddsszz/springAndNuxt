package kr.co.iamdesigner.web.apis;

import kr.co.iamdesigner.domain.application.PendantService;
import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.web.payload.PendantRegistrationPayload;
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
public class PendantApiController extends AbstractBaseController {
    private final PendantService service;

    @PostMapping("/api/pendant")
    public ResponseEntity<ApiResult> register(@Valid @RequestBody PendantRegistrationPayload payload, HttpServletRequest request){
        try {
            PendantRegisterCommand command = payload.toCommand();
            addTriggeredBy(command,request);
            service.register(command);
        }catch (Exception e){

        }
        return null;
    }

}
