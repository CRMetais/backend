package school.sptech.cr_metais.dto.PrecoProdutoTabela;

public class PrecoProdutoTabelaResponseDto {

    private Integer idProdutoPrecoTabela;
    private Integer idTabelaPreco;
    private Integer idProduto;
    private Double precoProduto;

    public PrecoProdutoTabelaResponseDto() {
    }

    public Integer getIdProdutoPrecoTabela() {
        return idProdutoPrecoTabela;
    }

    public void setIdProdutoPrecoTabela(Integer idProdutoPrecoTabela) {
        this.idProdutoPrecoTabela = idProdutoPrecoTabela;
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
