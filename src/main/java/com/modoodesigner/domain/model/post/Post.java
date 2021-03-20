package com.modoodesigner.domain.model.post;

import com.modoodesigner.domain.application.commands.PostRegisterCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String title;

    private String content;

    public Post(PostRegisterCommand command){
        title = command.getTitle();
        content = command.getContent();
    }
}
