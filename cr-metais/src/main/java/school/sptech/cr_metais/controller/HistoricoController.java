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

      // lambda
    @GetMapping("/xlsx")
    public ResponseEntity<String> gerarXlsx(
            @RequestParam String tipo,
            @RequestParam String dataInicio,
            @RequestParam String dataFim
    ) {

        String url = service.gerarXlsxLambda(tipo, dataInicio, dataFim);

        return ResponseEntity.ok(url);
    }

    // Baixar local
//    @GetMapping("/xlsx")
//    public ResponseEntity<byte[]> gerarXlsxLocal(
//            @RequestParam String tipo,
//            @RequestParam String dataInicio,
//            @RequestParam String dataFim
//    ) {
//
//        byte[] xlsx = service.gerarXlsxLocal(tipo, dataInicio, dataFim);
//
//        return ResponseEntity.ok()
//                .header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
//                .header("Content-Disposition", "attachment; filename=historico.xlsx")
//                .body(xlsx);
//    }

}