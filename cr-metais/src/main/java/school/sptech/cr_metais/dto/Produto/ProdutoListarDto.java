package school.sptech.cr_metais.dto.Produto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProdutoListarDto {

    @Schema(description = "Identificador do produto", example = "1")
    private Integer id;

    @Schema(description = "Nome do produto", example = "Cobre Mel")
    private String nome;

    @Schema(description = "Tipo do produto", example = "Cobre")
    private String tipoProduto;

    @Schema(description = "Preço do produto por kg", example = "10.00")
    private Double precoKg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Double getPrecoKg() {
        return precoKg;
    }

    public void setPrecoKg(Double precoKg) {
        this.precoKg = precoKg;
    }
}
