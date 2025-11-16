package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoCadastroDTO;
import school.sptech.cr_metais.dto.TabelaPreco.TabelaPrecoResponseDTO;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.service.TabelaPrecoService;

@RestController
@RequestMapping("/tabelasPreco")
@Tag(name = "TabelaPreco")
@SecurityRequirement(name = "Bearer")
public class TabelaPrecoController {

    @Autowired
    private final TabelaPrecoService tService;

    public TabelaPrecoController(TabelaPrecoService tService) {
        this.tService = tService;
    }

    @Operation(
            summary = "Cadastrar uma nova tabela de preço",
            description = "Cria uma tabela com base nas informações fornecidas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tabela cadastrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TabelaPreco.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })

    @PostMapping
    public ResponseEntity<TabelaPrecoResponseDTO> cadastrar(@RequestBody @Valid TabelaPrecoCadastroDTO dto) {

        TabelaPrecoResponseDTO resposta = tService.cadastrar(dto);

        return ResponseEntity.status(201).body(resposta);
    }
}


