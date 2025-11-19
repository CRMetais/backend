package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraRequestDto;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraResponseDto;
import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.ItemPedidoCompra;
import school.sptech.cr_metais.entity.Produto;

@Component
public class ItemPedidoCompraMapper {

    public ItemPedidoCompra toEntity(ItemPedidoCompraRequestDto dto, Compra compra, Produto produto) {
        ItemPedidoCompra item = new ItemPedidoCompra();

        item.setCompra(compra);
        item.setProduto(produto);
        item.setPesoKg(dto.getPesoKg());
        item.setPrecoUnitario(dto.getPrecoUnitario());

        return item;
    }

    public ItemPedidoCompraResponseDto toResponseDTO(ItemPedidoCompra item) {
        return new ItemPedidoCompraResponseDto(
                item.getId(),
                item.getCompra().getIdConta(),
                item.getProduto().getId(),
                item.getPesoKg(),
                item.getPrecoUnitario()
        );
    }
}
