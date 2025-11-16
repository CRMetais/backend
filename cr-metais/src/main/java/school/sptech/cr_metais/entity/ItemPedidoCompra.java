package school.sptech.cr_metais.entity;

import jakarta.persistence.*;

@Entity
public class ItemPedidoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "id_fk_compra")
//    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_fk_produto")
    private Produto produto;

    @Column(name = "peso_kg")
    private Double pesoKg;

    @Column(name = "preco_unitario")
    private Double precoUnitario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Compra getCompra() {
//        return compra;
//    }
//
//    public void setCompra(Compra compra) {
//        this.compra = compra;
//    }

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
