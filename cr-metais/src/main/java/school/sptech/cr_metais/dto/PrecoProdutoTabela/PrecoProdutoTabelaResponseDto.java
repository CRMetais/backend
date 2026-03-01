package school.sptech.cr_metais.dto.PrecoProdutoTabela;

public class PrecoProdutoTabelaResponseDto {

    private Integer fkTabelaPreco;
    private Integer fkProduto;
    private Double precoProduto;

    public PrecoProdutoTabelaResponseDto() {
    }

    public Integer getFkTabelaPreco() {
        return fkTabelaPreco;
    }

    public void setFkTabelaPreco(Integer fkTabelaPreco) {
        this.fkTabelaPreco = fkTabelaPreco;
    }

    public Integer getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(Integer fkProduto) {
        this.fkProduto = fkProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }
}
