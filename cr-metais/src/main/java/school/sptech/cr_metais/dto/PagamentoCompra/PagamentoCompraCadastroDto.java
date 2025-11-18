package school.sptech.cr_metais.dto.PagamentoCompra;

import jakarta.validation.constraints.NotNull;
import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.ContaPagamento;

import java.util.Date;

public class PagamentoCompraCadastroDto {


    private Date dataPagamento;
    private Integer idCompra;
    private Integer idContaPagamento;

    public PagamentoCompraCadastroDto(Date dataPagamento, Integer idCompra, Integer idContaPagamento) {
        this.dataPagamento = dataPagamento;
        this.idCompra = idCompra;
        this.idContaPagamento = idContaPagamento;
    }

    public PagamentoCompraCadastroDto() {
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
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