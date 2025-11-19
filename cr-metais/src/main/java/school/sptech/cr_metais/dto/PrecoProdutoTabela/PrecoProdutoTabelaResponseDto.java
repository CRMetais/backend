package school.sptech.cr_metais.dto.PrecoProdutoTabela;

public class PrecoProdutoTabelaResponseDto {

    private Integer id;
    private Integer idTabelaPreco;
    private Integer idProduto;
    private Double precoProduto;

    public PrecoProdutoTabelaResponseDto(Integer id, Integer idTabelaPreco, Integer idProduto, Double precoProduto) {
        this.id = id;
        this.idTabelaPreco = idTabelaPreco;
        this.idProduto = idProduto;
        this.precoProduto = precoProduto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
