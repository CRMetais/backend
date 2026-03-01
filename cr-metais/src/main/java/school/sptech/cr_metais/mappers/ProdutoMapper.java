package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.Produto.*;
import school.sptech.cr_metais.entity.Produto;

import java.util.List;

@Component
public class ProdutoMapper {

    public static Produto toEntity(ProdutoRequestDto dto) {

        if (dto == null){
            return null;
        }

        Produto p = new Produto();
        p.setNome(dto.getNome());
        p.setTipoProduto(dto.getTipoProduto());

        return p;
    }

    public static ProdutoResponseDto toResponse(Produto produto){

        if (produto == null){
            return null;
        }

        ProdutoResponseDto dto = new ProdutoResponseDto();

        dto.setIdProduto(produto.getIdProduto());
        dto.setNome(produto.getNome());
        dto.setTipoProduto(produto.getTipoProduto());

        return dto;
    }

    public static List<ProdutoResponseDto> toResponse(List<Produto> entidade){
        return entidade.stream().map(produto -> toResponse(produto)).toList();
    }
}
