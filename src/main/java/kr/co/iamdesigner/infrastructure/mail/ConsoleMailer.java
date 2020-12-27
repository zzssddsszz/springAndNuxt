package kr.co.iamdesigner.infrastructure.mail;

import kr.co.iamdesigner.domain.common.mail.Mailer;
import kr.co.iamdesigner.domain.common.mail.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("local")
@Component
public class ConsoleMailer implements Mailer {
    @Override
    public void send(Message message) {
        log.info("메시지 제목 : ["+message.getSubject()+"]");
        log.info("메시지 바디 : ["+message.getBody()+"]");
    }
}
