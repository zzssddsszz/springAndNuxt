package com.modoodesigner.web.results.data;

import com.modoodesigner.domain.model.post.Post;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostData {
    private Long id;
    private String title;
    private String content;

    public PostData(Post post) {
        id = post.getId();
        title = post.getTitle();
        content = post.getContent();
    }
}
