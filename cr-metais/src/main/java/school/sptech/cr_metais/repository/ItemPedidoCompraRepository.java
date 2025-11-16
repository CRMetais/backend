package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.cr_metais.entity.ItemPedidoCompra;

@Repository
public interface ItemPedidoCompraRepository extends JpaRepository<ItemPedidoCompra, Integer> {
}
