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
import school.sptech.cr_metais.dto.ContaPagamento.ContaPagamentoCadastroDto;
import school.sptech.cr_metais.dto.ContaPagamento.ContaPagamentoResponseDto;
import school.sptech.cr_metais.entity.ContaPagamento;
import school.sptech.cr_metais.service.ContaPagamentoService;

import java.util.List;

@RestController
@RequestMapping("/contas-pagamentos")
@Tag(name = "Contas Pagamento")
@SecurityRequirement(name = "Bearer")
public class ContaPagamentoController {

    @Autowired
    private final ContaPagamentoService contaPagamentoService;

    public ContaPagamentoController(ContaPagamentoService contaPagamentoService) {
        this.contaPagamentoService = contaPagamentoService;
    }

    @Operation(
            summary = "Cadastrar uma nova conta de pagamento",
            description = "Cria uma conta de pagamento com base nas informações fornecidas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Conta de pagamento cadastrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ContaPagamento.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ContaPagamentoResponseDto> cadastrar(
            @RequestBody @Valid ContaPagamentoCadastroDto dto) {

        ContaPagamentoResponseDto resposta = contaPagamentoService.cadastrar(dto);
        return ResponseEntity.status(201).body(resposta);
    }

    @Operation(
            summary = "Listar todas as contas de pagamento",
            description = "Retorna uma lista com todas as contas de pagamento cadastradas no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de contas retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ContaPagamento.class))),
            @ApiResponse(responseCode = "204", description = "Nenhuma conta de pagamento encontrada", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ContaPagamentoResponseDto>> listar() {

        List<ContaPagamentoResponseDto> todas = contaPagamentoService.listar();

        if (todas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(todas);
    }

    @Operation(
            summary = "Deletar conta de pagamento por ID",
            description = "Remove uma conta de pagamento do sistema com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Conta de pagamento deletada com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Conta de pagamento não encontrada", content = @Content)
    })
        @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        contaPagamentoService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @Operation(
            summary = "Buscar conta de pagamento por ID",
            description = "Busca uma conta de pagamento específica através do seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta de pagamento encontrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ContaPagamento.class))),
            @ApiResponse(responseCode = "404", description = "Conta de pagamento não encontrada", content = @Content)
    })
        @GetMapping("/{id:\\d+}")
    public ResponseEntity<ContaPagamentoResponseDto> buscarPorId(@PathVariable Integer id) {

        ContaPagamentoResponseDto dto = contaPagamentoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Atualizar conta de pagamento por ID",
            description = "Atualiza os dados de uma conta de pagamento existente com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta de pagamento atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ContaPagamento.class))),
            @ApiResponse(responseCode = "404", description = "Conta de pagamento não encontrada", content = @Content)
    })
        @PutMapping("/{id:\\d+}")
    public ResponseEntity<ContaPagamentoResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid ContaPagamentoCadastroDto dto) {

        ContaPagamentoResponseDto atualizada = contaPagamentoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }
}
