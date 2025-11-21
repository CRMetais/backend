package school.sptech.cr_metais.mappers;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraCadastroDto;
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraResponseDto;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.PagamentoCompra;

import java.util.ArrayList;
import java.util.List;

@Component
public class PagamentoCompraMapper {

    public static PagamentoCompra toEntity(PagamentoCompraCadastroDto dto) {

        if (dto == null) {

            return null;
        }

        PagamentoCompra pc = new PagamentoCompra();

        pc.setDataPagamentoCompra(dto.getDataPagamento());
        return pc;
    }

    public static PagamentoCompraResponseDto toResponse(PagamentoCompra entidade) {

        if (entidade == null) {
            return null;
        }

        PagamentoCompraResponseDto dto = new PagamentoCompraResponseDto();

        dto.setContaPagamento(entidade.getContaPagamento());
        dto.setDataPagamentoCompra(entidade.getDataPagamentoCompra());
        return dto;
    }

    public static List<PagamentoCompraResponseDto> toResponse(List<PagamentoCompra> entidade) {

        if (entidade == null) {
            return null;
        }

        List<PagamentoCompraResponseDto> dtos = new ArrayList<>();
        for (PagamentoCompra pagamentoCompra : entidade) {
            PagamentoCompraResponseDto response = toResponse(pagamentoCompra);
            dtos.add(response);
        }
        return dtos;
    }

}
