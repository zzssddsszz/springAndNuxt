package kr.co.iamdesigner.infrastructure.messaging;

import kr.co.iamdesigner.domain.common.event.DomainEvent;
import kr.co.iamdesigner.domain.common.event.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AmqpDomainEventPublisher implements DomainEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    @Qualifier("domainEventsExchange")
    private final FanoutExchange exchange;

    @Override
    public void publish(DomainEvent event) {
        log.debug("도메인 이벤트: " + event);
        try {
            rabbitTemplate.convertAndSend(exchange.getName(), "", event);
        } catch (AmqpException e) {
            log.error("MQ로 도메인 이벤트 전송 실패", e);
        }
    }
}
