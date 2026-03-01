package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.PrecoProdutoTabela.PrecoProdutoTabelaRequestDto;
import school.sptech.cr_metais.dto.PrecoProdutoTabela.PrecoProdutoTabelaResponseDto;
import school.sptech.cr_metais.entity.ItemPedidoVenda;
import school.sptech.cr_metais.entity.PrecoProdutoTabela;
import school.sptech.cr_metais.mappers.PrecoProdutoTabelaMapper;
import school.sptech.cr_metais.service.PrecoProdutoTabelaService;

import java.util.List;

@RestController
@RequestMapping({"/preco-produtos-tabelas", "/preco-produto-tabela"})
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Preço Produto Tabela")
@SecurityRequirement(name = "Bearer")
public class PrecoProdutoTabelaController {

    private final PrecoProdutoTabelaService precoProdutoTabelaService;

    public PrecoProdutoTabelaController(PrecoProdutoTabelaService precoProdutoTabelaService){
        this.precoProdutoTabelaService = precoProdutoTabelaService;
    }

    @Operation(
            summary = "Cadastrar um novo preço do produto de compra",
            description = "Cria um preço de produto com base nas informações fornecidas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Preço de produto cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<PrecoProdutoTabelaResponseDto> cadastrar(@RequestBody PrecoProdutoTabelaRequestDto dto){

        PrecoProdutoTabela precoParaRegistrar = PrecoProdutoTabelaMapper.toEntity(dto);
        PrecoProdutoTabela precoRegistrado = precoProdutoTabelaService.cadastrar(precoParaRegistrar, dto.getIdTabelaPreco(), dto.getIdProduto());
        PrecoProdutoTabelaResponseDto response = PrecoProdutoTabelaMapper.toResponse(precoRegistrado);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(
            summary = "Buscar preço de produto por ID",
            description = "Busca um preço de produto específico através do seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preço de produto encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "404", description = "Preço de produto não encontrado", content = @Content)
    })
        @GetMapping("/{id:\\d+}")
    public ResponseEntity<PrecoProdutoTabelaResponseDto> buscarPorId(@PathVariable Integer id){

        PrecoProdutoTabela precoProdutoTabela = precoProdutoTabelaService.buscarPorId(id);
        PrecoProdutoTabelaResponseDto response = PrecoProdutoTabelaMapper.toResponse(precoProdutoTabela);

        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "Listar todos os preços de produtos de compra",
            description = "Retorna uma lista com todos os preços de produtos cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de preços de produtos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "200", description = "Nenhum preço de produto encontrado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PrecoProdutoTabelaResponseDto>> listar(
            @RequestParam(required = false) Long tabelaPrecoId,
            @RequestParam(required = false) Long produtoId
    ){

        List<PrecoProdutoTabelaResponseDto> response = precoProdutoTabelaService.listar(tabelaPrecoId, produtoId);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "Atualizar preço de produto por ID",
            description = "Atualiza os dados de um preço de produto existente com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preço de produto atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "404", description = "Preço de produto não encontrado", content = @Content)
    })
        @PutMapping("/{id:\\d+}")
    public ResponseEntity<PrecoProdutoTabelaResponseDto> atualizar(
            @PathVariable Integer id, @RequestBody PrecoProdutoTabelaRequestDto dto){

        PrecoProdutoTabela entity = PrecoProdutoTabelaMapper.toEntity(dto);
        entity.setIdPrecoProdutoTabela(id);

        PrecoProdutoTabela precoProdutoTabelaAtualizado = precoProdutoTabelaService.atualizar(entity);
        PrecoProdutoTabelaResponseDto response = PrecoProdutoTabelaMapper.toResponse(precoProdutoTabelaAtualizado);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "Deletar preço de produto por ID",
            description = "Remove um preço de produto do sistema com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Preço de produto deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Preço de produto não encontrado", content = @Content)
    })
        @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){

        precoProdutoTabelaService.deletarPorId(id);
        return ResponseEntity.status(204).build();
    }

}
