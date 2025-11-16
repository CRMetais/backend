package school.sptech.cr_metais.service;

import school.sptech.cr_metais.entity.ItemPedidoCompra;
import school.sptech.cr_metais.repository.ItemPedidoCompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoCompraService {

    private final ItemPedidoCompraRepository repository;

    public ItemPedidoCompraService(ItemPedidoCompraRepository repository) {
        this.repository = repository;
    }

    public ItemPedidoCompra salvar(ItemPedidoCompra item) {
        return repository.save(item);
    }

    public List<ItemPedidoCompra> listarTodos() {
        return repository.findAll();
    }

    public Optional<ItemPedidoCompra> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
