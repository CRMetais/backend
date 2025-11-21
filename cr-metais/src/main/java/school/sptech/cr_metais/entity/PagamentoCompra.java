package school.sptech.cr_metais.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "pagamento_compra")
public class PagamentoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento_compra")
    private Integer idPagamentoCompra;

    @ManyToOne
    @JoinColumn(name = "fk_compra")
    private Compra compra;

    @Column(name = "data_pagamento_compra")
    private LocalDate dataPagamentoCompra;

    @ManyToOne
    @JoinColumn(name = "fk_conta_pagamento")
    private ContaPagamento contaPagamento;

    public PagamentoCompra(Integer idPagamentoCompra, Compra compra, LocalDate dataPagamentoCompra, ContaPagamento contaPagamento) {
        this.idPagamentoCompra = idPagamentoCompra;
        this.compra = compra;
        this.dataPagamentoCompra = dataPagamentoCompra;
        this.contaPagamento = contaPagamento;
    }

    public PagamentoCompra() {
    }

    public Integer getIdPagamentoCompra() {
        return idPagamentoCompra;
    }

    public void setIdPagamentoCompra(Integer idPagamentoCompra) {
        this.idPagamentoCompra = idPagamentoCompra;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public LocalDate getDataPagamentoCompra() {
        return dataPagamentoCompra;
    }

    public void setDataPagamentoCompra(LocalDate dataPagamentoCompra) {
        this.dataPagamentoCompra = dataPagamentoCompra;
    }

    public ContaPagamento getContaPagamento() {
        return contaPagamento;
    }

    public void setContaPagamento(ContaPagamento contaPagamento) {
        this.contaPagamento = contaPagamento;
    }
}
