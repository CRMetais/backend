package school.sptech.cr_metais.config.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Relatorio.RelatorioDTO;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitPropertiesConfiguration properties;

    public void send(RelatorioDTO dto) {
        rabbitTemplate.convertAndSend(
                properties.exchange().name(),
                properties.routingKey().name(),
                dto
        );
    }
}