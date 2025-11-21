package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraCadastroDto;
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraResponseDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioListarDto;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.PagamentoCompra;
import school.sptech.cr_metais.mappers.PagamentoCompraMapper;
import school.sptech.cr_metais.service.PagamentoCompraService;

import java.util.List;


@RequestMapping("/pagamento-compra")
@RestController
@Tag(name = "Pagamento Compra")
public class PagamentoCompraController {

    final PagamentoCompraService pagCompra;

    public PagamentoCompraController(PagamentoCompraService pagCompra) {
        this.pagCompra = pagCompra;
    }

    @Operation(
            summary = "Cadastrar um novo PagamentoCompra",
            description = "Cria um novo PagamentoCompra no sistema com base nas informações enviadas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "PagamentoCompra cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fornecedor.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou incompletos", content = @Content)
    })

    @PostMapping
    public ResponseEntity<PagamentoCompra> cadastrarPagamentoCompra(@RequestBody @Valid PagamentoCompraCadastroDto dto) {
        PagamentoCompra pagamentoCompraSalvo = pagCompra.cadastrar(dto);
        return ResponseEntity.status(201).body(pagamentoCompraSalvo);
    }

    @Operation(summary = "Listar todas os Pagamentoscompras", description = "Retorna uma lista de todos os Pagamentoscompras cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Pagamentoscompras retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioListarDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum Pagamentocompra encontrado")
    })

    @GetMapping
    public ResponseEntity<List<PagamentoCompraResponseDto>> listar() {

        List<PagamentoCompra> allPagamentoCompras = pagCompra.listar();
        if (allPagamentoCompras.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<PagamentoCompraResponseDto> response = PagamentoCompraMapper.toResponse(allPagamentoCompras);
        return ResponseEntity.status(200).body(response);
    }


}
