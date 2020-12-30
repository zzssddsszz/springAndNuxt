package kr.co.iamdesigner.domain.common.event;

public interface DomainEventPublisher {

    void publish(DomainEvent event);
}
