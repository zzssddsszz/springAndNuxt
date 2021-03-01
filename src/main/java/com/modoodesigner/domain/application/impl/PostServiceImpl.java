package com.modoodesigner.domain.application.impl;

import com.modoodesigner.domain.application.PostService;
import com.modoodesigner.domain.application.commands.PostRegisterCommand;
import com.modoodesigner.domain.model.post.Post;
import com.modoodesigner.domain.model.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    @Override
    public void register(PostRegisterCommand command) {
        Post post = new Post(command);
        repository.save(post);
    }
}
