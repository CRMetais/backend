package school.sptech.cr_metais.dto.Produto;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.cr_metais.entity.Produto;

public class ProdutoDetalhesDto {

    @Schema(description = "Nome do produto")
    private final String nome;

    @Schema(description = "Tipo do produto")
    private final String tipoProduto;

    @Schema(description = "Preço do produto por Kg")
    private final Double precoKg;

    public ProdutoDetalhesDto(Produto produto){
        this.nome = produto.getNome();
        this.tipoProduto = produto.getTipoProduto();
        this.precoKg = produto.getPrecoKg();
    }

    public String getNome() {
        return nome;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public Double getPrecoKg() {
        return precoKg;
    }
}
