package school.sptech.cr_metais.dto.Compra;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.cglib.core.Local;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraRequestDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CompraCadastroDto {

    private LocalDate dataCompra;

    @NotNull
    private Integer idFornecedor;

    @NotNull
    @Size(min = 1)
    private List<ItemPedidoCompraRequestDto> itens;

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

    public List<ItemPedidoCompraRequestDto> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoCompraRequestDto> itens) {
        this.itens = itens;
    }
}
