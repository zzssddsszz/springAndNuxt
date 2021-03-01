package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.PostService;
import com.modoodesigner.domain.application.commands.PostRegisterCommand;
import com.modoodesigner.web.payload.PostRegistrationPayload;
import com.modoodesigner.web.results.ApiResult;
import com.modoodesigner.web.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PostApiController {
    private final PostService service;

    @PostMapping("/api/posts")
    public ResponseEntity<ApiResult> register(@Valid @RequestBody PostRegistrationPayload payload,
                                              HttpServletRequest request){
        PostRegisterCommand command = payload.toCommand();
        service.register(command);

        return Result.created();
    }
}
