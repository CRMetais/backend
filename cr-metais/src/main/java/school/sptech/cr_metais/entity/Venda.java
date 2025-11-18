package school.sptech.cr_metais.entity;

import java.time.LocalDate;

public class Venda {

    private Integer idVenda;
    private Cliente fkCliente;
    private TabelaPreco fkTabelaPreco;
    private LocalDate dataVenda;

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

    public TabelaPreco getFkTabelaPreco() {
        return fkTabelaPreco;
    }

    public void setFkTabelaPreco(TabelaPreco fkTabelaPreco) {
        this.fkTabelaPreco = fkTabelaPreco;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
}
