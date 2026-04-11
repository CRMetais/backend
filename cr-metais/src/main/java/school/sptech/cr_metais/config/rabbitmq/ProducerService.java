package school.sptech.cr_metais.config.rabbitmq;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitPropertiesConfiguration properties;

    public void send(MessageDTO message) {
        String exchangeName = properties.exchange().name();
        String routingKey = "";

        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
