package school.sptech.cr_metais.config.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class BrokerController {

    private final ProducerService producerService;

    @PostMapping
    public ResponseEntity<Void> enviarMensagem(@RequestBody MessageDto message) {
        producerService.send(message);
        return ResponseEntity.status(202).build();
    }
}