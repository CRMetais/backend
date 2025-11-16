package school.sptech.cr_metais.entity;

import jakarta.persistence.*;

@Entity
public class PrecoProdutoTabela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // Ou outro campo identificador, se tiver

//    @ManyToOne
//    @JoinColumn(name = "fk_compra")
//    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "fk_tabela_preco")
    private TabelaPreco tabelaPreco;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;

    @Column(name = "preco_produto")
    private Double precoProduto;

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

    public TabelaPreco getTabelaPreco() {
        return tabelaPreco;
    }

    public void setTabelaPreco(TabelaPreco tabelaPreco) {
        this.tabelaPreco = tabelaPreco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }
}
