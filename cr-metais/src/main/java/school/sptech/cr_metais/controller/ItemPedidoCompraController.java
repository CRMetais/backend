package school.sptech.cr_metais.controller;

import school.sptech.cr_metais.entity.ItemPedidoCompra;
import school.sptech.cr_metais.service.ItemPedidoCompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-pedido-compra")
public class ItemPedidoCompraController {

    private final ItemPedidoCompraService service;

    public ItemPedidoCompraController(ItemPedidoCompraService service) {
        this.service = service;
    }

    @GetMapping
    public List<ItemPedidoCompra> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoCompra> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ItemPedidoCompra salvar(@RequestBody ItemPedidoCompra item) {
        return service.salvar(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoCompra> atualizar(@PathVariable Integer id, @RequestBody ItemPedidoCompra item) {
        return service.buscarPorId(id)
                .map(antigo -> {
                    item.setId(id);
                    return ResponseEntity.ok(service.salvar(item));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
