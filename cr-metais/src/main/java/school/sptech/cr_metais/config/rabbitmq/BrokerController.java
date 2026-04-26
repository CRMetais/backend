package school.sptech.cr_metais.config.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.cr_metais.dto.Relatorio.RelatorioDTO;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class BrokerController {


    private final ProducerService producerService;

    @PostMapping
    public ResponseEntity<Void> enviarMensagem(@RequestBody RelatorioDTO message) {
        producerService.send(message);
        return ResponseEntity.status(202).build();
    }

}
