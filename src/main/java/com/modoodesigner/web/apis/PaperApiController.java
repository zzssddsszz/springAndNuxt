package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.commands.PaperRegisterCommand;
import com.modoodesigner.web.payload.PaperRegistrationPayload;
import com.modoodesigner.web.results.ApiResult;
import com.modoodesigner.web.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class PaperApiController {

    @PostMapping("/api/paper")
    public ResponseEntity<ApiResult> createData(@RequestBody PaperRegistrationPayload payload) {
        try {
            PaperRegisterCommand command = payload.toCommand();
            return Result.created();
        } catch (Exception e) {
            return Result.failure("에러");
        }
    }
}
