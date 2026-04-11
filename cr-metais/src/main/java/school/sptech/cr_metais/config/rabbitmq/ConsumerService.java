package school.sptech.cr_metais.config.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @RabbitListener(queues = "${broker.queue.name}")
    public void receive(MessageDTO dto) {
        System.out.println("Received message: " + dto.message());
    }
}
