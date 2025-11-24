package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.Produto.*;
import school.sptech.cr_metais.entity.Estoque;
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

        Estoque e = new Estoque();
        e.setId(dto.getIdEstoque());
        p.setEstoque(e);

        return p;
    }

    public static ProdutoResponseDto toResponse(Produto produto){

        if (produto == null){
            return null;
        }

        ProdutoResponseDto dto = new ProdutoResponseDto();

        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setTipoProduto(produto.getTipoProduto());
        dto.setIdEstoque(produto.getEstoque().getId());

        return dto;
    }

    public static List<ProdutoResponseDto> toResponse(List<Produto> entidade){
        return entidade.stream().map(produto -> toResponse(produto)).toList();
    }
}
