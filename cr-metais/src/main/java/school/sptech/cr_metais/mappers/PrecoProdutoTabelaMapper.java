package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.PrecoProdutoTabela.PrecoProdutoTabelaRequestDto;
import school.sptech.cr_metais.dto.PrecoProdutoTabela.PrecoProdutoTabelaResponseDto;
import school.sptech.cr_metais.entity.PrecoProdutoTabela;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.entity.TabelaPreco;

import java.util.List;

@Component
public class PrecoProdutoTabelaMapper {

    public static PrecoProdutoTabela toEntity(PrecoProdutoTabelaRequestDto dto){

        if (dto == null){
            return null;
        }

        PrecoProdutoTabela precoProdutoTabela = new PrecoProdutoTabela();

        TabelaPreco tabelaPreco = new TabelaPreco();
        tabelaPreco.setIdTabela(dto.getIdTabelaPreco());
        precoProdutoTabela.setTabelaPreco(tabelaPreco);

        Produto produto = new Produto();
        produto.setId(dto.getIdProduto());
        precoProdutoTabela.setProduto(produto);

        precoProdutoTabela.setPrecoProduto(dto.getPrecoProduto());

        return precoProdutoTabela;
    }

    public static PrecoProdutoTabelaResponseDto toResponse(PrecoProdutoTabela precoProdutoTabela){

        if (precoProdutoTabela == null){
            return null;
        }

        PrecoProdutoTabelaResponseDto dto = new PrecoProdutoTabelaResponseDto();

        dto.setId(precoProdutoTabela.getId());
        dto.setPrecoProduto(precoProdutoTabela.getPrecoProduto());
        dto.setIdProduto(precoProdutoTabela.getProduto().getId());
        dto.setIdTabelaPreco(precoProdutoTabela.getTabelaPreco().getIdTabela());

        return dto;
    }

    public static List<PrecoProdutoTabelaResponseDto> toResponse(List<PrecoProdutoTabela> entidade){

        return entidade.stream().map(precoProdutoTabela -> toResponse(precoProdutoTabela)).toList();
    }

}
