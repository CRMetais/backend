package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.dto.Estoque.EstoqueRequestDto;
import school.sptech.cr_metais.dto.Estoque.EstoqueResponseDto;
import school.sptech.cr_metais.entity.Estoque;
import school.sptech.cr_metais.entity.ItemPedidoVenda;
import school.sptech.cr_metais.mappers.EstoqueMapper;
import school.sptech.cr_metais.service.strategy.EstoqueService;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@Tag(name = "Estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @Operation(
            summary = "Cadastrar um novo estoque",
            description = "Cria um estoque com base nas informações fornecidas no corpo da requisição."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estoque cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<EstoqueResponseDto> cadastrar(@RequestBody EstoqueRequestDto dto){

        Estoque estoqueParaRegistrar = EstoqueMapper.toEntity(dto);
        Estoque estoqueRegistrado = estoqueService.cadastrar(estoqueParaRegistrar);
        EstoqueResponseDto response = EstoqueMapper.toResponse(estoqueRegistrado);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(
            summary = "Buscar estoque por ID",
            description = "Busca um estoque específico através do seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponseDto> buscarPorId(@PathVariable Integer id){

        Estoque estoque = estoqueService.buscarPorId(id);
        EstoqueResponseDto response = EstoqueMapper.toResponse(estoque);

        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "Listar todos os estoques",
            description = "Retorna uma lista com todos os estoques no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de estoques retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum estoque encontrado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<EstoqueResponseDto>> listar(){

        List<Estoque> todos = estoqueService.listar();

        if (todos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<EstoqueResponseDto> response = EstoqueMapper.toResponse(todos);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "Atualizar estoque por ID",
            description = "Atualiza os dados de um estoque existente com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<EstoqueResponseDto> atualizar(@PathVariable Integer id, @RequestBody EstoqueRequestDto dto){

        Estoque entity = EstoqueMapper.toEntity(dto);
        entity.setId(id);

        Estoque estoqueAtualizado = estoqueService.atualizar(entity);
        EstoqueResponseDto response = EstoqueMapper.toResponse(estoqueAtualizado);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "Deletar estoque por ID",
            description = "Remove um estoque do sistema com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estoque deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        estoqueService.deletarPorId(id);

        return ResponseEntity.status(204).build();
    }
}
