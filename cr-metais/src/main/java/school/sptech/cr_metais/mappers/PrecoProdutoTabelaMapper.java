package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.PrecoProdutoTabela.PrecoProdutoTabelaRequestDto;
import school.sptech.cr_metais.dto.PrecoProdutoTabela.PrecoProdutoTabelaResponseDto;
import school.sptech.cr_metais.entity.PrecoProdutoTabela;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.entity.TabelaPreco;

@Component
public class PrecoProdutoTabelaMapper {

    public PrecoProdutoTabela toEntity(PrecoProdutoTabelaRequestDto dto, TabelaPreco tabelaPreco, Produto produto){
        PrecoProdutoTabela precoProdutoTabela = new PrecoProdutoTabela();
        precoProdutoTabela.setTabelaPreco(tabelaPreco);
        precoProdutoTabela.setProduto(produto);
        precoProdutoTabela.setPrecoProduto(dto.getPrecoProduto());
        return precoProdutoTabela;
    }

    public PrecoProdutoTabelaResponseDto toResponseDto(PrecoProdutoTabela precoProdutoTabela){
        return new PrecoProdutoTabelaResponseDto(
                precoProdutoTabela.getId(),
                precoProdutoTabela.getTabelaPreco().getIdTabela(),
                precoProdutoTabela.getProduto().getId(),
                precoProdutoTabela.getPrecoProduto()
        );
    }

}
