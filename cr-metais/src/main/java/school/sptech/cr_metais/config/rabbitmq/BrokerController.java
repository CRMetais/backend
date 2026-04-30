package school.sptech.cr_metais.config.rabbitmq;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.Relatorio.RelatorioDTO;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class BrokerController {

    private final ProducerService producerService;

    @PostMapping
    public ResponseEntity<Void> enviarMensagem(@RequestBody @Valid RelatorioDTO message) {
        // ✅ CORRIGIDO: adicionado @Valid para disparar validações do DTO
        producerService.send(message);
        return ResponseEntity.status(202).build();
    }
}