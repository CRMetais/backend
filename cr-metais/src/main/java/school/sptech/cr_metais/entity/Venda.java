package school.sptech.cr_metais.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

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
    @Column(name = "data_venda")
    private LocalDate dataVenda;
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemPedidoVenda> itens;


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

    public List<ItemPedidoVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoVenda> itens) {
        this.itens = itens;
    }
}
