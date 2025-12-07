package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraRequestDto;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraResponseDto;
import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.ItemPedidoCompra;
import school.sptech.cr_metais.entity.Produto;

import java.util.List;

@Component
public class ItemPedidoCompraMapper {

    public static ItemPedidoCompra toEntity(ItemPedidoCompraRequestDto dto) {

        if (dto == null){
            return null;
        }

        ItemPedidoCompra item = new ItemPedidoCompra();
        

        Produto produto = new Produto();
        produto.setIdProduto(dto.getIdProduto());
        item.setProduto(produto);

        item.setPesoKg(dto.getPesoKg());
        item.setPrecoUnitario(dto.getPrecoUnitario());

        return item;
    }

    public static ItemPedidoCompraResponseDto toResponse(ItemPedidoCompra item) {

        if (item == null){
            return null;
        }

        ItemPedidoCompraResponseDto dto = new ItemPedidoCompraResponseDto();

        dto.setId(item.getIdItemPedidoCompra());
        dto.setPesoKg(item.getPesoKg());
        dto.setPrecoUnitario(item.getPrecoUnitario());
        dto.setIdCompra(item.getCompra().getIdCompra());
        dto.setIdProduto(item.getProduto().getIdProduto());
        dto.setRendimento(item.getRendimento());

        return dto;
    }

    public static List<ItemPedidoCompraResponseDto> toResponse(List<ItemPedidoCompra> entidade){

        return entidade.stream().map(item -> toResponse(item)).toList();
    }
}
