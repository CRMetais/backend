package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.Produto.*;
import school.sptech.cr_metais.entity.Produto;

@Component
public class ProdutoMapper {

    public Produto toEntity(ProdutoCadastrarDto dto) {
        Produto p = new Produto();
        p.setNome(dto.getNome());
        p.setTipoProduto(dto.getTipoProduto());
        p.setPrecoKg(dto.getPrecoKg());
        return p;
    }

    public ProdutoListarDto toListarDto(Produto produto) {
        ProdutoListarDto dto = new ProdutoListarDto();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setTipoProduto(produto.getTipoProduto());
        dto.setPrecoKg(produto.getPrecoKg());
        return dto;
    }

    public ProdutoDetalhesDto toDetalhesDto(Produto produto) {
        return new ProdutoDetalhesDto(produto);
    }
}
