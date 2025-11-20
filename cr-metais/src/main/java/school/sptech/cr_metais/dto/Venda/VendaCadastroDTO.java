package school.sptech.cr_metais.dto.Venda;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class VendaCadastroDTO {
    @NotNull(message = "Cliente é obrigatório")
    private Integer idCliente;
    @NotNull(message = "Tabela de preço é obrigatória")
    private Integer idTabelaPreco;
    @NotNull(message = "Data de venda é obrigatória")
    private LocalDate datavenda;

    public VendaCadastroDTO(Integer idCliente, Integer idTabelaPreco, LocalDate datavenda) {
        this.idCliente = idCliente;
        this.idTabelaPreco = idTabelaPreco;
        this.datavenda = datavenda;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdTabelaPreco() {
        return idTabelaPreco;
    }

    public void setIdTabelaPreco(Integer idTabelaPreco) {
        this.idTabelaPreco = idTabelaPreco;
    }

    public LocalDate getDatavenda() {
        return datavenda;
    }

    public void setDatavenda(LocalDate datavenda) {
        this.datavenda = datavenda;
    }
}
