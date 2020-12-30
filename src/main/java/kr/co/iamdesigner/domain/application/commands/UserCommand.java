package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.common.event.TriggeredBy;
import kr.co.iamdesigner.domain.model.user.UserId;
import kr.co.iamdesigner.utils.IpAddress;
import lombok.*;
import org.springframework.util.Assert;

import java.util.Objects;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public abstract class UserCommand implements TriggeredBy {

    private UserId userId;
    private IpAddress ipAddress;

    public void triggeredBy(UserId userId, IpAddress ipAddress) {
        this.userId = userId;
        this.ipAddress = ipAddress;
    }
}
