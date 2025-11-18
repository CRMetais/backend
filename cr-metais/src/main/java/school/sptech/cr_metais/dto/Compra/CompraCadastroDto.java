package school.sptech.cr_metais.dto.Compra;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class CompraCadastroDto {

    private Date dataCompra;

    @NotNull
    private Integer idFornecedor;

    public CompraCadastroDto(Date dataCompra, Integer idFornecedor) {
        this.dataCompra = dataCompra;
        this.idFornecedor = idFornecedor;
    }

    public CompraCadastroDto() {
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

}
