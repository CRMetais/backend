package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.Venda.VendaCadastroDTO;
import school.sptech.cr_metais.dto.Venda.VendaResponseDTO;
import school.sptech.cr_metais.entity.ItemPedidoVenda;
import school.sptech.cr_metais.entity.Venda;
import school.sptech.cr_metais.entity.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendaMapper {

    public Venda toEntity(VendaCadastroDTO dto) {
        Venda v = new Venda();
        v.setDataVenda(dto.getDatavenda());

        if (dto.getItens() != null) {
            List<ItemPedidoVenda> itens = dto.getItens().stream().map(itemDto -> {
                ItemPedidoVenda item = new ItemPedidoVenda();

                Produto p = new Produto();
                p.setIdProduto(itemDto.getIdProduto());

                item.setProduto(p);
                item.setPesoKg(itemDto.getPesoKg());
                item.setPrecoUnitario(itemDto.getPrecoUnitario());
                item.setVenda(v);

                return item;
            }).collect(Collectors.toList());

            v.setItens(itens);
        } else {
            v.setItens(new ArrayList<>());
        }

        return v;
    }

    public VendaResponseDTO toResponseDTO(Venda v) {
        return new VendaResponseDTO(
                v.getIdVenda(),
                v.getFkCliente(),
                v.getDataVenda()
        );
    }
}