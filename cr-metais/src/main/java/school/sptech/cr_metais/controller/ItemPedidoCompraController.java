package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraRequestDto;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraResponseDto;
import school.sptech.cr_metais.entity.ItemPedidoCompra;
import school.sptech.cr_metais.entity.ItemPedidoVenda;
import school.sptech.cr_metais.mappers.ItemPedidoCompraMapper;
import school.sptech.cr_metais.service.ItemPedidoCompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-pedido-compras")
@Tag(name = "Item Pedido Compra")
public class ItemPedidoCompraController {

    private final ItemPedidoCompraService itemPedidoCompraService;

    public ItemPedidoCompraController(ItemPedidoCompraService itemPedidoCompraService) {
        this.itemPedidoCompraService = itemPedidoCompraService;
    }



    @Operation(
            summary = "Buscar item por ID",
            description = "Busca um item específico através do seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoCompraResponseDto> buscarPorId(@PathVariable Integer id){

        ItemPedidoCompra item = itemPedidoCompraService.buscarPorId(id);
        ItemPedidoCompraResponseDto response = ItemPedidoCompraMapper.toResponse(item);

        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "Listar todos os itens de compra",
            description = "Retorna uma lista com todos os itens cadastrados no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de itens retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum item encontrado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ItemPedidoCompraResponseDto>> listar(){

        List<ItemPedidoCompra> todos = itemPedidoCompraService.listar();

        if (todos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<ItemPedidoCompraResponseDto> response = ItemPedidoCompraMapper.toResponse(todos);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "Atualizar item por ID",
            description = "Atualiza os dados de um item existente com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemPedidoVenda.class))),
            @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoCompraResponseDto> atualizar(@PathVariable Integer id, @RequestBody ItemPedidoCompraRequestDto dto){

        ItemPedidoCompra entity = ItemPedidoCompraMapper.toEntity(dto);
        entity.setIdItemPedidoCompra(id);

        ItemPedidoCompra itemAtualizado = itemPedidoCompraService.atualizar(entity);
        ItemPedidoCompraResponseDto response = ItemPedidoCompraMapper.toResponse(itemAtualizado);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(
            summary = "Deletar item por ID",
            description = "Remove um item do sistema com base no seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        itemPedidoCompraService.deletarPorId(id);

        return ResponseEntity.status(204).build();
    }

}
