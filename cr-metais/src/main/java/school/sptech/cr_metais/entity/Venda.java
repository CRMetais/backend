package school.sptech.cr_metais.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenda;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente fkCliente;
    @NotNull
    @OneToOne
    @JoinColumn(name = "fk_tabela_preco")
    private TabelaPreco fkTabelaPreco;
    @NotNull
    @Column(name = "data_venda")
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
