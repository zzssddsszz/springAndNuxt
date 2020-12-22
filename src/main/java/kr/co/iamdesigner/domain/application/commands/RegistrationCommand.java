package kr.co.iamdesigner.domain.application.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class RegistrationCommand extends AnonymousCommand {

    private final String username;
    private final String emailAddress;
    private final String password;


}
