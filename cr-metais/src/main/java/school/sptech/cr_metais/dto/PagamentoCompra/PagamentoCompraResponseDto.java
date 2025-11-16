package school.sptech.cr_metais.dto.PagamentoCompra;

import java.util.Date;

public class PagamentoCompraResponseDto {

    private Date dataPagamentoCompra;

    public PagamentoCompraResponseDto(Date dataPagamentoCompra) {
        this.dataPagamentoCompra = dataPagamentoCompra;
    }

    public PagamentoCompraResponseDto() {
    }

    public Date getDataPagamentoCompra() {
        return dataPagamentoCompra;
    }

    public void setDataPagamentoCompra(Date dataPagamentoCompra) {
        this.dataPagamentoCompra = dataPagamentoCompra;
    }
}
