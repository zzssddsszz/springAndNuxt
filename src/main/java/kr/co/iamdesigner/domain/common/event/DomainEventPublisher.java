package kr.co.iamdesigner.domain.common.event;

import kr.co.iamdesigner.domain.model.user.events.UserRegisteredEvent;

public interface DomainEventPublisher {

    void publish(DomainEvent event);
}
