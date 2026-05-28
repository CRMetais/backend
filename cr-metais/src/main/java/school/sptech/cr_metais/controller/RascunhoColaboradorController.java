package school.sptech.cr_metais.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.Usuario.RascunhoColaboradorDto;
import school.sptech.cr_metais.service.RascunhoColaboradorService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/cache")
 public class RascunhoColaboradorController {

    private final RascunhoColaboradorService service;

    public RascunhoColaboradorController(RascunhoColaboradorService service) {
        this.service = service;
    }

    // GET /api/cache/{chave}
    @GetMapping("/{chave}")
    public ResponseEntity<RascunhoColaboradorDto> buscar(@PathVariable String chave) {
        RascunhoColaboradorDto dto = service.buscar(chave);
        return dto != null
                ? ResponseEntity.ok(dto)
                : ResponseEntity.noContent().build(); // 204 se não existir
    }

    // POST /api/cache/{chave}
    @PostMapping("/{chave}")
    public ResponseEntity<Void> salvar(
            @PathVariable String chave,
            @RequestBody RascunhoColaboradorDto dto) {
        service.salvar(chave, dto);
        return ResponseEntity.ok().build();
    }

    // DELETE /api/cache/{chave}
    @DeleteMapping("/{chave}")
    public ResponseEntity<Void> deletar(@PathVariable String chave) {
        service.deletar(chave);
        return ResponseEntity.noContent().build();
    }
}
