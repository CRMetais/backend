package school.sptech.cr_metais.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraRequestDto;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraResponseDto;
import school.sptech.cr_metais.entity.ItemPedidoCompra;
import school.sptech.cr_metais.service.ItemPedidoCompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-pedido-compras")
public class ItemPedidoCompraController {

    private final ItemPedidoCompraService itemPedidoCompraService;

    public ItemPedidoCompraController(ItemPedidoCompraService itemPedidoCompraService) {
        this.itemPedidoCompraService = itemPedidoCompraService;
    }

    @PostMapping
    public ResponseEntity<ItemPedidoCompraResponseDto> cadastrar(@RequestBody @Valid ItemPedidoCompraRequestDto dto){

        ItemPedidoCompraResponseDto resposta = itemPedidoCompraService.salvar(dto);
        return ResponseEntity.status(201).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoCompraResponseDto>> listar(){

        List<ItemPedidoCompraResponseDto> todos = itemPedidoCompraService.listarTodos();

        if (todos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoCompraResponseDto> buscarPorId(@PathVariable Integer id){
        ItemPedidoCompraResponseDto dto = itemPedidoCompraService.buscarPorId(id);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoCompraResponseDto> atualizar(@PathVariable Integer id, @RequestBody @Valid ItemPedidoCompraRequestDto dto){
        ItemPedidoCompraResponseDto atualizado = itemPedidoCompraService.atualizar(id,dto);
        return ResponseEntity.status(200).body(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        itemPedidoCompraService.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
