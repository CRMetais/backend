package school.sptech.cr_metais.dto.Produto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import school.sptech.cr_metais.entity.Produto;

public class ProdutoDetalhesDto {

    @Schema(description = "Nome do produto")
    private String nome;

    @Schema(description = "Tipo do produto")
    private String tipoProduto;

    @Schema(description = "Preço do produto por Kg")
    private Double precoKg;

    @NotNull
    @Schema(description = "Id do estoque")
    private Integer idEstoque;

    public ProdutoDetalhesDto(String nome, String tipoProduto, Double precoKg, Integer idEstoque) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.precoKg = precoKg;
        this.idEstoque = idEstoque;
    }

    public ProdutoDetalhesDto(Produto produto) {
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

    public Integer getIdEstoque(){return idEstoque;}
}
