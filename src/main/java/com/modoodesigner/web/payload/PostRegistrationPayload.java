package com.modoodesigner.web.payload;

import com.modoodesigner.domain.application.commands.PostRegisterCommand;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostRegistrationPayload {
    @NotNull
    private String title;
    private String content;

    public PostRegisterCommand toCommand() {
        return PostRegisterCommand.builder()
                .title(title)
                .content(content)
                .build();
    }
}
