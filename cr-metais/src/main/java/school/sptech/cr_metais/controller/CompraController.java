package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.Compra.CompraCadastroDto;
import school.sptech.cr_metais.dto.Compra.CompraResponseDto;
import school.sptech.cr_metais.dto.ContaPagamento.ContaPagamentoCadastroDto;
import school.sptech.cr_metais.dto.ContaPagamento.ContaPagamentoResponseDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioListarDto;
import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.ContaPagamento;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.PagamentoCompra;
import school.sptech.cr_metais.mappers.CompraMapper;
import school.sptech.cr_metais.service.CompraService;

import java.util.List;

@RequestMapping("/compra")
@RestController
@Tag(name = "Compra")
@SecurityRequirement(name = "Bearer")
public class CompraController {

    final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @Operation(
            summary = "Cadastrar uma nova Compra",
            description = "Cria uma nova compra no sistema com base nas informações enviadas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Compra cadastrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fornecedor.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou incompletos", content = @Content)
    })

    @PostMapping
    public ResponseEntity<CompraResponseDto> cadastrarCompra(@RequestBody @Valid CompraCadastroDto dto){

        CompraResponseDto compraSalva = compraService.cadastrar(dto);
        return ResponseEntity.status(201).body(compraSalva);
    }

    @Operation(summary = "Listar todas as compras", description = "Retorna uma lista de todos as compras cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de compras retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioListarDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhuma compra encontrado")
    })

    @GetMapping
    public ResponseEntity<List<CompraResponseDto>> listar() {
        List<CompraResponseDto> allCompras = compraService.listar();
        if (allCompras.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(allCompras);
    }

    @Operation(
            summary = "Deletar compra por ID",
            description = "Remove uma compra do sistema com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Compra deletada com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Compra não encontrada", content = @Content)
    })
        @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        compraService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @Operation(
            summary = "Buscar compra por ID",
            description = "Busca uma Compra específica através do seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra encontrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ContaPagamento.class))),
            @ApiResponse(responseCode = "404", description = "Compra não encontrada", content = @Content)
    })
        @GetMapping("/{id:\\d+}")
    public ResponseEntity<CompraResponseDto> buscarPorId(@PathVariable Integer id) {

        CompraResponseDto dto = compraService.buscarPorId(id);
        return ResponseEntity.status(200).body(dto);
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
    public ResponseEntity<CompraResponseDto> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid CompraCadastroDto dto) {

        CompraResponseDto atualizada = compraService.atualizar(id, dto);
        return ResponseEntity.status(200).body(atualizada);
    }


}
