package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.Venda.VendaCadastroDTO;
import school.sptech.cr_metais.dto.Venda.VendaResponseDTO;
import school.sptech.cr_metais.entity.Venda;

@Component
public class VendaMapper {
    public Venda toEntity(VendaCadastroDTO dto){
        Venda v = new Venda();
        v.setDataVenda(dto.getDatavenda());
        return v;
    }

    public VendaResponseDTO toResponseDTO(Venda v){
       return new VendaResponseDTO(
               v.getIdVenda(),
               v.getFkCliente(),
               v.getFkTabelaPreco(),
               v.getDataVenda()
       );
    }
}
