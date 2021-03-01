package com.modoodesigner.domain.application;

import com.modoodesigner.domain.application.commands.PostRegisterCommand;

public interface PostService {
    void register(PostRegisterCommand command);
}
