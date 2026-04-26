package school.sptech.cr_metais.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.config.rabbitmq.ProducerService;
import school.sptech.cr_metais.dto.Relatorio.RelatorioDTO;


@RestController
@RequestMapping("/teste")
@RequiredArgsConstructor
public class TesteController {

    private final ProducerService producer;

    @PostMapping
    public String enviar(@RequestBody RelatorioDTO dto) {
        producer.send(dto);
        return "Mensagem enviada para fila";
    }
}