package school.sptech.cr_metais.dto.PagamentoCompra;

import school.sptech.cr_metais.entity.ContaPagamento;

import java.util.Date;

public class PagamentoCompraResponseDto {

    // Fica de presente

    private Date dataPagamentoCompra;
    private ContaPagamento contaPagamento;

    public PagamentoCompraResponseDto(Date dataPagamentoCompra, ContaPagamento contaPagamento) {
        this.dataPagamentoCompra = dataPagamentoCompra;
        this.contaPagamento = contaPagamento;
    }

    public PagamentoCompraResponseDto() {
    }

    public ContaPagamento getContaPagamento() {
        return contaPagamento;
    }

    public void setContaPagamento(ContaPagamento contaPagamento) {
        this.contaPagamento = contaPagamento;
    }

    public Date getDataPagamentoCompra() {
        return dataPagamentoCompra;
    }

    public void setDataPagamentoCompra(Date dataPagamentoCompra) {
        this.dataPagamentoCompra = dataPagamentoCompra;
    }
}
