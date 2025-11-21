package school.sptech.cr_metais.dto.PagamentoCompra;

import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.ContaPagamento;

import java.time.LocalDate;
import java.util.Date;

public class PagamentoCompraCadastroDto {


    private LocalDate dataPagamento;
    private Integer idCompra;
    private Integer idContaPagamento;

    public PagamentoCompraCadastroDto(LocalDate dataPagamento, Integer idCompra, Integer idContaPagamento) {
        this.dataPagamento = dataPagamento;
        this.idCompra = idCompra;
        this.idContaPagamento = idContaPagamento;
    }

    public PagamentoCompraCadastroDto() {
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdContaPagamento() {
        return idContaPagamento;
    }

    public void setIdContaPagamento(Integer idContaPagamento) {
        this.idContaPagamento = idContaPagamento;
    }
}