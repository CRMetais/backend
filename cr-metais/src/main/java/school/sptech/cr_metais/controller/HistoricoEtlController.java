package school.sptech.cr_metais.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.Historico.TransacaoHistoricoDto;
import school.sptech.cr_metais.service.HistoricoEtlService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/historico")
public class HistoricoEtlController {

    private final HistoricoEtlService service;

    public HistoricoEtlController(HistoricoEtlService service) {
        this.service = service;
    }

    @GetMapping("/csv-extract")
    public ResponseEntity<List<TransacaoHistoricoDto>> extrair(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @RequestParam(defaultValue = "TODOS") String tipo) {

        return ResponseEntity.ok(service.buscarTudo(dataInicio, dataFim, tipo));
    }
}