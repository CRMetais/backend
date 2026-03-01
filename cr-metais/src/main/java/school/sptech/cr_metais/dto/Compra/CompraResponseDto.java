package school.sptech.cr_metais.dto.Compra;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CompraResponseDto {

    private LocalDate dataCompra;

    @NotNull
    private Integer idFornecedor;

    @NotNull
    private Integer idCompra;

    public CompraResponseDto(LocalDate dataCompra, Integer idFornecedor, Integer idCompra) {
        this.dataCompra = dataCompra;
        this.idFornecedor = idFornecedor;
        this.idCompra = idCompra;
    }

    public CompraResponseDto() {
    }
    public Integer getIdCompra(){ return idCompra;}

    public void setIdCompra(Integer idCompra){ this.idCompra = idCompra; }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
}

