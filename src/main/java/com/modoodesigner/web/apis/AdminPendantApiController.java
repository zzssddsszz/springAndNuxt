package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.PendantService;
import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.model.part.common.PartRegistrationException;
import com.modoodesigner.web.results.ApiResult;
import com.modoodesigner.web.results.Result;
import com.modoodesigner.domain.model.part.common.PartExistsException;
import com.modoodesigner.web.payload.PendantRegistrationPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminPendantApiController extends AbstractBaseController {
    private final PendantService service;

    @PostMapping("/api/pendant")
    public ResponseEntity<ApiResult> register(@Valid @RequestBody PendantRegistrationPayload payload, HttpServletRequest request) {
        try {
            PendantRegisterCommand command = payload.toCommand();
            addTriggeredBy(command,request);
            service.register(command);
            return Result.created();
        }catch (PartRegistrationException e) {
            String errorMessage = "등록실패하였습니다.";
          if (e instanceof PartExistsException){
              errorMessage = "팬던트가 이미 등록되어있습니다.";
          }
            return Result.failure(errorMessage);
        }
    }

}