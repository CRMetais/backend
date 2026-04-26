package school.sptech.cr_metais.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Relatorio.RelatorioDTO;

@Service
public class RelatorioProducer {

    private final RabbitTemplate rabbitTemplate;

    public RelatorioProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviar(RelatorioDTO dto) {
        rabbitTemplate.convertAndSend("fila-email", dto);
    }
}
