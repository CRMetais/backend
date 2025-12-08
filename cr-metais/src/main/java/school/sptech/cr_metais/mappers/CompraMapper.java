package school.sptech.cr_metais.mappers;


import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.Compra.CompraCadastroDto;
import school.sptech.cr_metais.dto.Compra.CompraResponseDto;
import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.Fornecedor;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompraMapper {

    public static Compra toEntity(CompraCadastroDto dto) {

        if (dto == null) {

            return null;

        }
        Compra c = new Compra();

        c.setDataCompra(dto.getDataCompra());

        return c;
    }

    public static CompraResponseDto toResponse(Compra entidade) {

        if (entidade == null) {
            return null;
        }

        CompraResponseDto dto = new CompraResponseDto();

        dto.setDataCompra(entidade.getDataCompra());
        dto.setIdFornecedor(entidade.getFornecedor().getIdFornecedor());
        return dto;
    }

    public static List<CompraResponseDto> toResponse(List<Compra> entidades) {

        List<CompraResponseDto> dtos = new ArrayList<>();

        for (Compra compra : entidades) {
            CompraResponseDto response = toResponse(compra);
            dtos.add(response);
        }
        return dtos;
    }

}
