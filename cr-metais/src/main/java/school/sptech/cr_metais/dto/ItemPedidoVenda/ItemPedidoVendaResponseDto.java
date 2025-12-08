package school.sptech.cr_metais.dto.ItemPedidoVenda;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.entity.Venda;

public class ItemPedidoVendaResponseDto {

    @Schema(description = "Identificador da tabela", example = "1")
    private Integer idItemVenda;

    @Schema(description = "Venda relacionada com o item")
    private Venda venda;

    @Schema(description = "Produto relacionado com o item")
    private Produto produto;

    @Schema(description = "Peso do item de venda", example = "10.2")
    private Double pesoKg;

    @Schema(description = "Preço do item na venda", example = "4.30")
    private Double precoUnitario;

    @Schema(description =  "Rendimento extra na venda do produto", example = "45.67")
    private Double rendimentoExtra;

    public ItemPedidoVendaResponseDto(Integer idItemVenda, Venda venda, Produto produto, Double pesoKg, Double precoUnitario, Double rendimentoExtra) {
        this.idItemVenda = idItemVenda;
        this.venda = venda;
        this.produto = produto;
        this.pesoKg = pesoKg;
        this.precoUnitario = precoUnitario;
        this.rendimentoExtra = rendimentoExtra;
    }

    public ItemPedidoVendaResponseDto (){};

    public Integer getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(Integer idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public Double getRendimentoExtra() {
        return rendimentoExtra;
    }

    public void setRendimentoExtra(Double rendimentoExtra) {
        this.rendimentoExtra = rendimentoExtra;
    }
}
