package school.sptech.cr_metais.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class ItemPedidoVenda {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idItemVenda;

        @ManyToOne
        @JoinColumn(name = "id_fk_venda")
        private Venda venda;

        @ManyToOne
        @JoinColumn(name = "id_fk_produto")
        private Produto produto;

        @Column(name = "peso_kg")
        @NotNull
        @Positive
        private Double pesoKg;

        @Column(name = "preco_unitario")
        @NotNull
        @Positive
        private Double precoUnitario;

        @Column(name = "rendimento_extra")
        private Double rendimentoExtra;


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


