package school.sptech.cr_metais.dto.ItemPedidoCompra;

import jakarta.validation.constraints.NotNull;

public class ItemPedidoCompraRequestDto {


    @NotNull
    Integer idCompra;

    @NotNull
    Integer idProduto;

    @NotNull
    private Double pesoKg;

    @NotNull
    private Double precoUnitario;


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
