package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.common.event.TriggeredBy;
import kr.co.iamdesigner.domain.model.user.UserId;
import kr.co.iamdesigner.utils.IpAddress;
import org.springframework.util.Assert;

import java.util.Objects;

public abstract class UserCommand implements TriggeredBy {

    private UserId userId;
    private IpAddress ipAddress;

    public void triggeredBy(UserId userId, IpAddress ipAddress) {
        this.userId = userId;
        this.ipAddress = ipAddress;
    }

    @Override
    public UserId getUserId() {
        return userId;
    }

    @Override
    public IpAddress getIpAddress() {
        return ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCommand)) return false;
        UserCommand that = (UserCommand) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(ipAddress, that.ipAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, ipAddress);
    }
}
