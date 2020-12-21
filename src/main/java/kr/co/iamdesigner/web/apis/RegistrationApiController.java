package kr.co.iamdesigner.web.apis;

import kr.co.iamdesigner.domain.application.UserService;
import kr.co.iamdesigner.web.results.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationApiController extends AbstractBaseController{
    private final UserService service;

    @PostMapping("/api/registrations")
    public ResponseEntity<ApiResult> register() {

    }
}
