package school.sptech.cr_metais.config.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final EmailService emailService;

    @RabbitListener(queues = "${broker.queue.name}")
    public void receive(MessageDto dto) {
        System.out.println("Received message: " + dto.message());
        emailService.enviar(dto.message());
    }
}
