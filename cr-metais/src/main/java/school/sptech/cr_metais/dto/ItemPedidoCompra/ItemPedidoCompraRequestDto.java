package school.sptech.cr_metais.dto.ItemPedidoCompra;

import jakarta.validation.constraints.NotNull;

public class ItemPedidoCompraRequestDto {

    @NotNull
    private Integer idProduto;

    @NotNull
    private Double pesoKg;

    @NotNull
    private Double precoUnitario;

    public ItemPedidoCompraRequestDto(Integer idCompra, Integer idProduto, Double pesoKg, Double precoUnitario) {
        this.idProduto = idProduto;
        this.pesoKg = pesoKg;
        this.precoUnitario = precoUnitario;
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
