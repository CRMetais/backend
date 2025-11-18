package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import school.sptech.cr_metais.dto.Fornecedor.FornecedorCadastroDto;
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraCadastroDto;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.PagamentoCompra;
import school.sptech.cr_metais.service.PagamentoCompraService;

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
            @ApiResponse(responseCode = "201", description = "Fornecedor cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fornecedor.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou incompletos", content = @Content)
    })

    @PostMapping
    public ResponseEntity<PagamentoCompra> cadastrarPagamentoCOmpra(@RequestBody @Valid PagamentoCompraCadastroDto dto){

        PagamentoCompra pagamentoCompraSalvo = pagCompra.cadastrar(dto);
        return ResponseEntity.status(201).body(pagamentoCompraSalvo);

    }




}
