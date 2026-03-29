package school.sptech.cr_metais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.cr_metais.service.HistoricoService;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService service;

    @GetMapping
    public ResponseEntity<?> listarPorTipo(@RequestParam String tipo) {

        return ResponseEntity.ok(service.listarPorTipo(tipo));
    }
}
