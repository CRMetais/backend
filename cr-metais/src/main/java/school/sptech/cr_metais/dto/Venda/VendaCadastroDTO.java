package school.sptech.cr_metais.dto.Venda;

import jakarta.validation.constraints.NotNull;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraRequestDto;

import java.time.LocalDate;
import java.util.List;

public class VendaCadastroDTO {
    @NotNull(message = "Cliente é obrigatório")
    private Integer idCliente;
    @NotNull(message = "Data de venda é obrigatória")
    private LocalDate datavenda;
    private List<ItemPedidoCompraRequestDto> itens;

    public VendaCadastroDTO(Integer idCliente, LocalDate datavenda) {
        this.idCliente = idCliente;
        this.datavenda = datavenda;
    }

    public VendaCadastroDTO() {

    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getDatavenda() {
        return datavenda;
    }

    public void setDatavenda(LocalDate datavenda) {
        this.datavenda = datavenda;
    }

    public List<ItemPedidoCompraRequestDto> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoCompraRequestDto> itens) {
        this.itens = itens;
    }
}
