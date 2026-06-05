package school.sptech.cr_metais.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.service.HistoricoService;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService service;

    @GetMapping
    public ResponseEntity<Page<?>> listarPorTipo(
            @RequestParam String tipo,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho
    ) {

        Page<?> resultado = service.listarPorTipo(tipo, pagina, tamanho);

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/xml")
    public ResponseEntity<String> gerarXml(
            @RequestParam String tipo,
            @RequestParam String dataInicio,
            @RequestParam String dataFim
    ) {

        String url = service.gerarXmlLambda(tipo, dataInicio, dataFim);

        return ResponseEntity.ok(url);
    }

    // Baixar local
//    @GetMapping("/xml")
//    public ResponseEntity<String> gerarXml(
//            @RequestParam String tipo,
//            @RequestParam String dataInicio,
//            @RequestParam String dataFim
//    ) {
//
//        String xml = service.gerarXmlLambda(tipo, dataInicio, dataFim);
//
//        return ResponseEntity.ok()
//                .header("Content-Type", "application/xml")
//                .header("Content-Disposition", "attachment; filename=historico.xml")
//                .body(xml);
//        }

}