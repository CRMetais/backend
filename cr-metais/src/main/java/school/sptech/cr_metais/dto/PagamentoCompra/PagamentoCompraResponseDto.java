package school.sptech.cr_metais.dto.PagamentoCompra;

import school.sptech.cr_metais.entity.ContaPagamento;

import java.time.LocalDate;
import java.util.Date;

public class PagamentoCompraResponseDto {

    // Fica de presente

    private LocalDate dataPagamentoCompra;
    private ContaPagamento contaPagamento;


    public PagamentoCompraResponseDto(LocalDate dataPagamentoCompra, ContaPagamento contaPagamento) {
        this.dataPagamentoCompra = dataPagamentoCompra;
        this.contaPagamento = contaPagamento;
    }

    public PagamentoCompraResponseDto() {
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
