package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.PaperService;
import com.modoodesigner.domain.application.commands.PaperPendantRegisterCommand;
import com.modoodesigner.domain.application.commands.PaperRegisterCommand;
import com.modoodesigner.domain.application.commands.PaperTextRegisterCommand;
import com.modoodesigner.domain.common.security.CurrentUser;
import com.modoodesigner.domain.model.user.User;
import com.modoodesigner.web.payload.paper.PaperPendantRegistrationPayload;
import com.modoodesigner.web.payload.paper.PaperRegistrationPayload;
import com.modoodesigner.web.payload.paper.PaperTextRegistrationPayload;
import com.modoodesigner.web.results.ApiResult;
import com.modoodesigner.web.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class PaperApiController extends AbstractBaseController {
    private final PaperService service;

    @PostMapping("/api/papers")
    public ResponseEntity<ApiResult> register(
            @RequestBody PaperRegistrationPayload payload,
            HttpServletRequest request) {
        try {
            PaperRegisterCommand command = payload.toCommand();
            addTriggeredBy(command, request);

            return Result.created();
        } catch (Exception e) {
            return Result.failure("에러");
        }
    }

    @PostMapping("/api/papers")
    public ResponseEntity<ApiResult> register(
            @CurrentUser User user,
            @RequestBody PaperRegistrationPayload payload) {
        try {
            PaperRegisterCommand command = payload.toCommand();
            return Result.created();
        } catch (Exception e) {
            return Result.failure("에러");
        }
    }

    @PostMapping("/api/paper/pendants")
    public ResponseEntity<ApiResult> register(
            @RequestBody PaperPendantRegistrationPayload payload) {
        try {
            PaperPendantRegisterCommand command = payload.toCommand();
            return Result.created();
        } catch (Exception e) {
            return Result.failure("에러");
        }
    }

    @PostMapping("/api/paper/texts")
    public ResponseEntity<ApiResult> register(
            @RequestBody PaperTextRegistrationPayload payload) {
        try {
            PaperTextRegisterCommand command = payload.toCommand();
            return Result.created();
        } catch (Exception e) {
            return Result.failure("에러");
        }
    }
}
