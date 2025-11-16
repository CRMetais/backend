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

import java.util.List;

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


    @Operation(
            summary = "Listar todos as tabelas",
            description = "Retorna uma lista com todos as tabelas cadastradas no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tabelas retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TabelaPreco.class))),
            @ApiResponse(responseCode = "204", description = "Nenhuma tabela encontrada", content = @Content)
    })
    // Listar todos as tabelas
    @GetMapping
    public ResponseEntity<List<TabelaPrecoResponseDTO>> listar() {

        List<TabelaPrecoResponseDTO> all = tService.listar();

        if (all.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(all);
    }


    @Operation(
            summary = "Deletar tabela por ID",
            description = "Remove uma tabela do sistema com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tabela deletada com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Tabela não encontrada", content = @Content)
    })
    // Deletar tabela por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        tService.deletar(id);
        return ResponseEntity.status(204).build();
    }


    @Operation(
            summary = "Buscar tabela por ID",
            description = "Busca uma tabela específica através do seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tabela encontrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TabelaPreco.class))),
            @ApiResponse(responseCode = "404", description = "Tabela não encontrada", content = @Content)
    })
    // Buscar tabela por id
    @GetMapping("/{id}")
    public ResponseEntity<TabelaPrecoResponseDTO> buscarPorId(@PathVariable Integer id) {

        TabelaPrecoResponseDTO dto = tService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }


    @Operation(
            summary = "Atualizar tabela por ID",
            description = "Atualiza os dados de uma tabela existente com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tabela atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TabelaPreco.class))),
            @ApiResponse(responseCode = "404", description = "Tabela não encontrada", content = @Content)
    })
    // Atualizar tabela
    @PutMapping("{id}")
    public ResponseEntity<TabelaPrecoResponseDTO> atualizar(
            @PathVariable Integer id,
            @RequestBody TabelaPrecoCadastroDTO dto) {

        TabelaPrecoResponseDTO atualizada = tService.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

}


