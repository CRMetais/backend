package school.sptech.cr_metais.dto.Venda;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class VendaCadastroDTO {
    @NotNull(message = "Cliente é obrigatório")
    private Integer idCliente;
    @NotNull(message = "Data de venda é obrigatória")
    private LocalDate datavenda;

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
}
