package kr.co.iamdesigner.domain.common.mail;

public interface Message {
    String getTo();
    String getSubject();
    String getBody();
    String getFrom();
}
