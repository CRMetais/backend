package school.sptech.cr_metais.dto.Compra;

import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

public class CompraCadastroDto {

    private LocalDate dataCompra;

    @NotNull
    private Integer idFornecedor;

    public CompraCadastroDto(LocalDate dataCompra, Integer idFornecedor) {
        this.dataCompra = dataCompra;
        this.idFornecedor = idFornecedor;
    }

    public CompraCadastroDto() {
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
