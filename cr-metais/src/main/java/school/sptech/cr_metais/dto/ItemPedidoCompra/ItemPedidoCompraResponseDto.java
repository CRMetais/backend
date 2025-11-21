package school.sptech.cr_metais.dto.ItemPedidoCompra;

import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.Produto;

public class ItemPedidoCompraResponseDto {

    private Integer id;
    private Integer idCompra;
    private Integer idProduto;
    private Double pesoKg;
    private Double precoUnitario;

    public ItemPedidoCompraResponseDto() {
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
