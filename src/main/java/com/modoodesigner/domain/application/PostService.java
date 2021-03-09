package com.modoodesigner.domain.application;

import com.modoodesigner.domain.application.commands.PostRegisterCommand;
import com.modoodesigner.domain.model.post.Post;

import java.util.List;

public interface PostService {
    void register(PostRegisterCommand command);
    List<Post> getAll();
}
