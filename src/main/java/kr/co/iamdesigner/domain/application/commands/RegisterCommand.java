package kr.co.iamdesigner.domain.application.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterCommand extends AnonymousCommand{
    private String username;
    private String emailAddress;
    private String password;

}
