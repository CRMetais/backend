package school.sptech.cr_metais.dto.PagamentoCompra;

import jakarta.validation.constraints.NotNull;
import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.ContaPagamento;

import java.util.Date;

public class PagamentoCompraCadastroDto {

    private Integer idPagamentoCompra;
    private Compra compra;
    private Date dataPagamento;
    private ContaPagamento contaPagamento;

    public PagamentoCompraCadastroDto(Integer idPagamentoCompra, Compra compra, Date dataPagamento, ContaPagamento contaPagamento) {
        this.idPagamentoCompra = idPagamentoCompra;
        this.compra = compra;
        this.dataPagamento = dataPagamento;
        this.contaPagamento = contaPagamento;
    }

    public PagamentoCompraCadastroDto() {
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

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public ContaPagamento getContaPagamento() {
        return contaPagamento;
    }

    public void setContaPagamento(ContaPagamento contaPagamento) {
        this.contaPagamento = contaPagamento;
    }
}
