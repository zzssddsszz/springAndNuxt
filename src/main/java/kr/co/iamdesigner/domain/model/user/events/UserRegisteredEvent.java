package kr.co.iamdesigner.domain.model.user.events;

import kr.co.iamdesigner.domain.common.event.DomainEvent;
import kr.co.iamdesigner.domain.common.event.TriggeredFrom;
import kr.co.iamdesigner.domain.model.user.User;

public class UserRegisteredEvent extends DomainEvent {
    public UserRegisteredEvent(User user, TriggeredFrom triggeredFrom) {
        super(user.getId(), triggeredFrom);
    }

    @Override
    public String toString() {
        return "UserRegisteredEvent{userId=" + getUserId() + '}';
    }

}
