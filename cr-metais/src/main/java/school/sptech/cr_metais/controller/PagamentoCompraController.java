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
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraCadastroDto;
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraResponseDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioListarDto;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.service.PagamentoCompraService;

import java.util.List;


@RequestMapping("/pagamento-compra")
@RestController
@Tag(name = "Pagamento Compra")
@SecurityRequirement(name = "Bearer")
public class PagamentoCompraController {

    final PagamentoCompraService pagCompra;

    public PagamentoCompraController(PagamentoCompraService pagCompra) {
        this.pagCompra = pagCompra;
    }

    @Operation(
            summary = "Cadastrar um novo Pagamento de Compra",
            description = "Cria um novo Pagamento de Compra no sistema com base nas informações enviadas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pagamento de Compra cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fornecedor.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou incompletos", content = @Content)
    })

    @PostMapping
    public ResponseEntity<PagamentoCompraResponseDto> cadastrarPagamentoCompra(@RequestBody @Valid PagamentoCompraCadastroDto dto) {
        PagamentoCompraResponseDto pagamentoCompraSalvo = pagCompra.cadastrar(dto);
        return ResponseEntity.status(201).body(pagamentoCompraSalvo);
    }

    @Operation(summary = "Listar todas os Pagamento de Compra", description = "Retorna uma lista de todos os Pagamento de Compra cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Pagamento de Compra retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioListarDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum Pagamentocompra encontrado")
    })

    @GetMapping
    public ResponseEntity<List<PagamentoCompraResponseDto>> listar() {

        List<PagamentoCompraResponseDto> todas = pagCompra.listar();

        if (todas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(todas);
    }

    @Operation(
            summary = "Deletar Pagamento de Compra por ID",
            description = "Remove uma conta de pagamento do sistema com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pagamento de Compra deletada com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pagamento de Compra não encontrada", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        pagCompra.deletar(id);
        return ResponseEntity.status(204).build();
    }

}
