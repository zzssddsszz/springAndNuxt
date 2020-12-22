package kr.co.iamdesigner.domain.application.commands;

import kr.co.iamdesigner.domain.common.event.TriggeredFrom;
import kr.co.iamdesigner.utils.IpAddress;
import lombok.EqualsAndHashCode;
import org.springframework.util.Assert;

@EqualsAndHashCode
public class AnonymousCommand implements TriggeredFrom {

    private IpAddress ipAddress;

    public void triggeredBy(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }
    @Override
    public IpAddress getIpAddress() {
        return ipAddress;
    }
}