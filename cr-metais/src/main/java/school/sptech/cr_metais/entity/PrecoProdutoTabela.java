package school.sptech.cr_metais.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class PrecoProdutoTabela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_preco_produto_tabela")
    private Integer idPrecoProdutoTabela;

    @ManyToOne
    @JoinColumn(name = "fk_tabela_preco")
    private TabelaPreco tabelaPreco;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private Produto produto;

    @NotNull
    @Positive
    @Column(name = "preco_produto")
    private Double precoProduto;

    public Integer getIdPrecoProdutoTabela() {
        return idPrecoProdutoTabela;
    }

    public void setIdPrecoProdutoTabela(Integer idPrecoProdutoTabela) {
        this.idPrecoProdutoTabela = idPrecoProdutoTabela;
    }

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
