package school.sptech.cr_metais.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Integer idCompra;

    @ManyToOne
    @JoinColumn(name = "fk_fornecedor")
    private Fornecedor fornecedor;

    @Column(name = "data_compra")
    private LocalDate dataCompra;

    public Compra(Integer idConta, Fornecedor fornecedor, LocalDate dataCompra) {
        this.idCompra = idConta;
        this.fornecedor = fornecedor;
        this.dataCompra = dataCompra;
    }

    public Compra() {
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }
}
