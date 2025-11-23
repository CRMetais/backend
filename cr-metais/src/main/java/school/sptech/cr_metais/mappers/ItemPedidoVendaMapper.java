package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.ItemPedidoVenda.ItemPedidoVendaCadastroDto;
import school.sptech.cr_metais.dto.ItemPedidoVenda.ItemPedidoVendaResponseDto;
import school.sptech.cr_metais.entity.ItemPedidoVenda;

@Component
public class ItemPedidoVendaMapper {

    public ItemPedidoVenda toEntity (ItemPedidoVendaCadastroDto dto){
        ItemPedidoVenda itemNovo = new ItemPedidoVenda();
        itemNovo.setPesoKg(dto.getPesoKg());
        itemNovo.setPrecoUnitario(dto.getPrecoUnitario());

        return itemNovo;
    }

    public ItemPedidoVendaResponseDto toDto (ItemPedidoVenda entity){
        ItemPedidoVendaResponseDto dto = new ItemPedidoVendaResponseDto(
                entity.getIdItemVenda(),
                entity.getVenda(),
                entity.getProduto(),
                entity.getPesoKg(),
                entity.getPrecoUnitario()
        );

        return dto;
    }
}
