package kr.co.iamdesigner.domain.application.commands;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterCommand extends AnonymousCommand{
    private String username;
    private String emailAddress;
    private String password;

}
