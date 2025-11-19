package school.sptech.cr_metais.dto.ItemPedidoCompra;

public class ItemPedidoCompraResponseDto {

    private Integer id;
    private Integer idCompra;
    private Integer idProduto;
    private Double pesoKg;
    private Double precoUnitario;

    public ItemPedidoCompraResponseDto(Integer id, Integer idCompra, Integer idProduto, Double pesoKg, Double precoUnitario) {
        this.id = id;
        this.idCompra = idCompra;
        this.idProduto = idProduto;
        this.pesoKg = pesoKg;
        this.precoUnitario = precoUnitario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
