package school.sptech.cr_metais.dto.Compra;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CompraResponseDto {

    private LocalDate dataCompra;

    @NotNull
    private Integer idFornecedor;

    public CompraResponseDto(LocalDate dataCompra, Integer idFornecedor) {
        this.dataCompra = dataCompra;
        this.idFornecedor = idFornecedor;
    }

    public CompraResponseDto() {
    }

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
