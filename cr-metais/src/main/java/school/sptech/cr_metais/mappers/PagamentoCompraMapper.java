package school.sptech.cr_metais.mappers;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraCadastroDto;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.PagamentoCompra;

@Component
public class PagamentoCompraMapper {

    public PagamentoCompra toEntity(PagamentoCompraCadastroDto dto){

        if (dto == null){

            return null;
        }

        PagamentoCompra pc = new PagamentoCompra();

        pc.setDataPagamentoCompra(dto.getDataPagamento());
        return pc;
    }
}
