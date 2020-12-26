package kr.co.iamdesigner.domain.common.mail;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class SimpleMessage implements Message {
    private final String to;
    private final String subject;
    private final String body;
    private final String from;

    @Override
    public String getTo() {
        return null;
    }

    @Override
    public String getSubject() {
        return null;
    }

    @Override
    public String getBody() {
        return null;
    }

    @Override
    public String getFrom() {
        return null;
    }
}
