package school.sptech.cr_metais.dto.PagamentoCompra;

import school.sptech.cr_metais.entity.ContaPagamento;

import java.time.LocalDate;
import java.util.Date;

public class PagamentoCompraResponseDto {

    // Fica de presente
    private Integer idPagamentoCompra;
    private LocalDate dataPagamentoCompra;
    private ContaPagamento contaPagamento;


    public PagamentoCompraResponseDto(Integer idPagamentoCompra, LocalDate dataPagamentoCompra, ContaPagamento contaPagamento) {
        this.idPagamentoCompra = idPagamentoCompra;
        this.dataPagamentoCompra = dataPagamentoCompra;
        this.contaPagamento = contaPagamento;
    }

    public PagamentoCompraResponseDto() {
    }

    public Integer getIdPagamentoCompra() {
        return idPagamentoCompra;
    }

    public void setIdPagamentoCompra(Integer idPagamentoCompra) {
        this.idPagamentoCompra = idPagamentoCompra;
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
