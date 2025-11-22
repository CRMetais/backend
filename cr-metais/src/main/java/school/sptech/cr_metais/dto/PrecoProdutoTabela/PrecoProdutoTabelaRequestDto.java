package school.sptech.cr_metais.dto.PrecoProdutoTabela;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PrecoProdutoTabelaRequestDto {

    @NotNull
    private Integer idTabelaPreco;

    @NotNull
    private Integer idProduto;

    @NotNull
    @Positive
    private Double precoProduto;

    public PrecoProdutoTabelaRequestDto(Integer idTabelaPreco, Integer idProduto, Double precoProduto) {
        this.idTabelaPreco = idTabelaPreco;
        this.idProduto = idProduto;
        this.precoProduto = precoProduto;
    }

    public Integer getIdTabelaPreco() {
        return idTabelaPreco;
    }

    public void setIdTabelaPreco(Integer idTabelaPreco) {
        this.idTabelaPreco = idTabelaPreco;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }
}
