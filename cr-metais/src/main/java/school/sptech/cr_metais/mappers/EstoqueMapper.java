package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.Estoque.EstoqueRequestDto;
import school.sptech.cr_metais.dto.Estoque.EstoqueResponseDto;
import school.sptech.cr_metais.entity.Estoque;
import school.sptech.cr_metais.entity.Produto;

import java.util.List;

@Component
public class EstoqueMapper {


    public static Estoque toEntity(EstoqueRequestDto dto){
        if (dto == null){
            return null;
        }

        Estoque estoque = new Estoque();
        estoque.setQuantidadeDisponivel(dto.getQuantidadeDisponivel());
        estoque.setLocalizacao(dto.getLocalizacao());

        Produto produto = new Produto();
        produto.setIdProduto(dto.getIdProduto());
        estoque.setProduto(produto);

        return estoque;
    }

    public static EstoqueResponseDto toResponse(Estoque estoque){
        if (estoque == null){
            return null;
        }

        EstoqueResponseDto dto = new EstoqueResponseDto();
        dto.setIdEstoque(estoque.getIdEstoque());
        dto.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel());
        dto.setLocalizacao(estoque.getLocalizacao());
        if (estoque.getProduto() != null) {
            dto.setIdProduto(estoque.getProduto().getIdProduto());
        }

        return dto;
    }

    public static List<EstoqueResponseDto> toResponse(List<Estoque> entidade){
        return entidade.stream().map(estoque -> toResponse(estoque)).toList();

    }

}
