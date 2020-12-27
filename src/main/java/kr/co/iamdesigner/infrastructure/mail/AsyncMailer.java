package kr.co.iamdesigner.infrastructure.mail;

import kr.co.iamdesigner.domain.common.mail.Mailer;
import kr.co.iamdesigner.domain.common.mail.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AsyncMailer implements Mailer {

    private final JavaMailSender javaMailSender;

    @Async
    @Override
    public void send(Message message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            if (StringUtils.isNoneBlank(message.getFrom())) {
                mailMessage.setFrom(message.getFrom());
            }
            if (StringUtils.isNoneBlank(message.getFrom())) {
                mailMessage.setFrom(message.getFrom());
            }
        }
    }
}
