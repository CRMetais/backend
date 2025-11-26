package school.sptech.cr_metais.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class ItemPedidoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItemPedidoCompra;

    @ManyToOne
    @JoinColumn(name = "id_fk_compra")
    private Compra compra;

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

    public Integer getIdItemPedidoCompra() {
        return idItemPedidoCompra;
    }

    public void setIdItemPedidoCompra(Integer idItemPedidoCompra) {
        this.idItemPedidoCompra = idItemPedidoCompra;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
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
}
