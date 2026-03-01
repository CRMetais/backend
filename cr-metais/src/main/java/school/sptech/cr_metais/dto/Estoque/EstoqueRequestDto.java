package school.sptech.cr_metais.dto.Estoque;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EstoqueRequestDto {

    @NotNull
    private Integer quantidadeDisponivel;

    @NotBlank
    private String localizacao;

    @NotNull
    private Integer idProduto;

    public EstoqueRequestDto(Integer quantidadeDisponivel, String localizacao, Integer idProduto) {
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.localizacao = localizacao;
        this.idProduto = idProduto;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }
}
