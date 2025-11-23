package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraRequestDto;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraResponseDto;
import school.sptech.cr_metais.entity.ItemPedidoCompra;
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

    @PostMapping
    public ResponseEntity<ItemPedidoCompraResponseDto> cadastrar(@RequestBody ItemPedidoCompraRequestDto dto){

        ItemPedidoCompra itemParaRegistrar = ItemPedidoCompraMapper.toEntity(dto);
        ItemPedidoCompra itemRegistrado = itemPedidoCompraService.cadastrar(itemParaRegistrar, dto.getIdCompra(), dto.getIdProduto());
        ItemPedidoCompraResponseDto response = ItemPedidoCompraMapper.toResponse(itemRegistrado);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoCompraResponseDto> buscarPorId(@PathVariable Integer id){

        ItemPedidoCompra item = itemPedidoCompraService.buscarPorId(id);
        ItemPedidoCompraResponseDto response = ItemPedidoCompraMapper.toResponse(item);

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoCompraResponseDto>> listar(){

        List<ItemPedidoCompra> todos = itemPedidoCompraService.listar();

        if (todos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<ItemPedidoCompraResponseDto> response = ItemPedidoCompraMapper.toResponse(todos);
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoCompraResponseDto> atualizar(@PathVariable Integer id, @RequestBody ItemPedidoCompraRequestDto dto){

        ItemPedidoCompra entity = ItemPedidoCompraMapper.toEntity(dto);
        entity.setId(id);

        ItemPedidoCompra itemAtualizado = itemPedidoCompraService.atualizar(entity);
        ItemPedidoCompraResponseDto response = ItemPedidoCompraMapper.toResponse(itemAtualizado);
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        itemPedidoCompraService.deletarPorId(id);

        return ResponseEntity.status(204).build();
    }

}
