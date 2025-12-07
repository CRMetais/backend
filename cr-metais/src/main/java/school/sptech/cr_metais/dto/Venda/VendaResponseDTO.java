package school.sptech.cr_metais.dto.Venda;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.cr_metais.entity.Cliente;
import school.sptech.cr_metais.entity.TabelaPreco;

import java.time.LocalDate;

public class VendaResponseDTO {

     @Schema(description = "Identificador da tabela", example = "1")
     private Integer idVenda;

    @Schema(description = "Cliente referente a venda", example = "Vital")

    private Cliente fkCliente;

    @Schema(description = "Data da venda", example = "20/11/2024")

    private LocalDate dataVenda;

    public VendaResponseDTO() {
    }

    public VendaResponseDTO(Integer idVenda, Cliente fkCliente, LocalDate dataVenda) {
        this.idVenda = idVenda;
        this.fkCliente = fkCliente;
        this.dataVenda = dataVenda;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Cliente getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Cliente fkCliente) {
        this.fkCliente = fkCliente;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
}
