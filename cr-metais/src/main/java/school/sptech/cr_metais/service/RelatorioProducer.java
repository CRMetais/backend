package school.sptech.cr_metais.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.config.rabbitmq.MessageDto;
import school.sptech.cr_metais.config.rabbitmq.RabbitPropertiesConfiguration;

@Service
public class RelatorioProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitPropertiesConfiguration properties;

    public RelatorioProducer(RabbitTemplate rabbitTemplate, RabbitPropertiesConfiguration properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    public void enviar(String message) {
        MessageDto dto = new MessageDto(message);
        rabbitTemplate.convertAndSend(
                properties.exchange().name(),
                "",
                dto
        );
    }
}
