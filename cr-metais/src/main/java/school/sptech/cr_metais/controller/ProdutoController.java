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
import school.sptech.cr_metais.dto.Produto.*;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.mappers.ProdutoMapper;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.service.ProdutoService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos")
@SecurityRequirement(name = "Bearer")
public class ProdutoController {

private final ProdutoService pService;

    public ProdutoController(ProdutoService pService) {
        this.pService = pService;
    }


    @Operation(
            summary = "Cadastrar um novo produto",
            description = "Cria um novo produto com base nas informações fornecidas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    // Cadastrar produtos
    @PostMapping
    public ResponseEntity<ProdutoResponseDto> cadastrar(@RequestBody ProdutoRequestDto dto) {

        Produto produtoParaCadastrar = ProdutoMapper.toEntity(dto);
        Produto produtoRegistrado = pService.cadastrar(produtoParaCadastrar, dto.getIdEstoque());
        ProdutoResponseDto response = ProdutoMapper.toResponse(produtoRegistrado);

        return ResponseEntity.status(201).body(response);
    }

    @Operation(
            summary = "Buscar produto por ID",
            description = "Busca um produto específico através do seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> buscarPorId(@PathVariable Integer id) {

        Produto produto = pService.buscarPorId(id);
        ProdutoResponseDto response = ProdutoMapper.toResponse(produto);

        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "Listar todos os produtos",
            description = "Retorna uma lista com todos os produtos cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado", content = @Content)
    })
    // Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listar() {

        List<Produto> todos = pService.listar();

        if (todos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<ProdutoResponseDto> response = ProdutoMapper.toResponse(todos);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "Atualizar produto por ID",
            description = "Atualiza os dados de um produto existente com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    // Atualizar Produto
    @PutMapping("{id}")
    public ResponseEntity<ProdutoResponseDto> atualizar(@PathVariable Integer id,  @RequestBody ProdutoRequestDto dto) {

        Produto entity = ProdutoMapper.toEntity(dto);
        entity.setId(id);

        Produto produtoAtualizado = pService.atualizar(entity);
        ProdutoResponseDto response = ProdutoMapper.toResponse(produtoAtualizado);

        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "Deletar produto por ID",
            description = "Remove um produto do sistema com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    // Deletar todos os produtos
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        pService.deletar(id);
        return ResponseEntity.status(204).build();
    }

}
