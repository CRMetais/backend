package school.sptech.cr_metais.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.config.rabbitmq.ProducerService;
import school.sptech.cr_metais.dto.Relatorio.RelatorioDTO;
import school.sptech.cr_metais.service.RelatorioService;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class RelatorioJob {

    private final RelatorioService service;
    private final ProducerService producer;

    @Scheduled(cron = "0 0 0 * * *") //Todos os dias 00:00
    public void executar() {

        String html = service.gerarHtml();

        RelatorioDTO dto = new RelatorioDTO(
                "financeiro@empresa.com",
                html
        );

        producer.send(dto);
    }
}