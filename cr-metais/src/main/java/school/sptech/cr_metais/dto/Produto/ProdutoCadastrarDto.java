package school.sptech.cr_metais.dto.Produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProdutoCadastrarDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String tipoProduto;

    @NotNull
    @Positive
    private Double precoKg;

    @NotNull
    private Integer idEstoque;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTipoProduto() { return tipoProduto; }
    public void setTipoProduto(String tipoProduto) { this.tipoProduto = tipoProduto; }
    public Double getPrecoKg() { return precoKg; }
    public void setPrecoKg(Double precoKg) { this.precoKg = precoKg; }
    public Integer getIdEstoque() {
        return idEstoque;
    }
    public void setIdEstoque(Integer idEstoque) {
        this.idEstoque = idEstoque;
    }

}
