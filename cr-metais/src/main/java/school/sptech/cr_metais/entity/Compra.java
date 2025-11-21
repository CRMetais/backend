package school.sptech.cr_metais.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compras")
    private Integer idConta;

    @ManyToOne
    @JoinColumn(name = "fk_fornecedor")
    private Fornecedor fornecedor;

    @Column(name = "data_compra")
    private LocalDate dataCompra;

    public Compra(Integer idConta, Fornecedor fornecedor, LocalDate dataCompra) {
        this.idConta = idConta;
        this.fornecedor = fornecedor;
        this.dataCompra = dataCompra;
    }

    public Compra() {
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
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
