package school.sptech.cr_metais.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.Boleta.BoletaCacheDTO;
import school.sptech.cr_metais.service.BoletaRascunhoService;

import java.util.List;

@RestController
@RequestMapping("/boletas/rascunho")
@RequiredArgsConstructor
public class BoletaRascunhoController {

    private final BoletaRascunhoService rascunhoService;

    /**
     * Recupera o rascunho de boletas salvo no Redis para o usuário autenticado.
     * GET /boletas/rascunho
     */
    @GetMapping
    public ResponseEntity<List<BoletaCacheDTO>> obterRascunho(
            @AuthenticationPrincipal UserDetails userDetails) {

        // Se o Spring Security não estiver configurado com UserDetails,
        // você pode receber uma String com o username vinda do seu token.
        String username = userDetails.getUsername();

        List<BoletaCacheDTO> rascunho = rascunhoService.buscarRascunho(username);
        return ResponseEntity.ok(rascunho);
    }

    /**
     * Salva ou atualiza o estado atual de todas as abas de boletas no Redis.
     * POST /boletas/rascunho
     */
    @PostMapping
    public ResponseEntity<List<BoletaCacheDTO>> salvarRascunho(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody List<BoletaCacheDTO> boletas) {

        String username = userDetails.getUsername();
        List<BoletaCacheDTO> rascunhoSalvo = rascunhoService.salvarRascunho(username, boletas);

        return ResponseEntity.ok(rascunhoSalvo);
    }

    /**
     * Deleta o rascunho do Redis.
     * DELETE /boletas/rascunho
     */
    @DeleteMapping
    public ResponseEntity<Void> limparRascunho(
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        rascunhoService.limparRascunho(username);

        return ResponseEntity.noContent().build();
    }
}
