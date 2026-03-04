package school.sptech.cr_metais.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.cr_metais.entity.TransacaoEtl;
import school.sptech.cr_metais.service.ExtrairDadosUnificadosUseCase;

import java.util.List;

@RestController
@RequestMapping("/api/etl")
public class EtlController {

    private final ExtrairDadosUnificadosUseCase useCase;

    public EtlController(ExtrairDadosUnificadosUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/full-extract")
    public ResponseEntity<List<TransacaoEtl>> getData() {
        List<TransacaoEtl> dados = useCase.executar();
        return ResponseEntity.ok(dados);
    }
}