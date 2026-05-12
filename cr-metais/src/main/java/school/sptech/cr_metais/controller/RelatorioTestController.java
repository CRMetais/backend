package school.sptech.cr_metais.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.cr_metais.service.RelatorioJob;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioTestController {

    private final RelatorioJob relatorioJob;

    @PostMapping("/disparar")
    public ResponseEntity<String> disparar() {
        relatorioJob.executar();
        return ResponseEntity.ok("Relatório enviado para a fila!");
    }
}