package school.sptech.cr_metais.dto.ItemPedidoVenda;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.entity.Venda;

public class ItemPedidoVendaCadastroDto {

    @NotNull
    private Integer fk_venda;

    @NotNull
    private Integer fk_produto;

    @NotNull
    @Positive
    private Double pesoKg;

    @NotNull
    @Positive
    private Double precoUnitario;

    public Integer getFk_venda() {
        return fk_venda;
    }

    public void setFk_venda(Integer fk_venda) {
        this.fk_venda = fk_venda;
    }

    public Integer getFk_produto() {
        return fk_produto;
    }

    public void setFk_produto(Integer fk_produto) {
        this.fk_produto = fk_produto;
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
