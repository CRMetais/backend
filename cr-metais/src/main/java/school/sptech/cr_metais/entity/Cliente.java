package school.sptech.cr_metais.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;

    @NotNull
    @CNPJ
    @NotBlank
    private String cnpj;

    @NotBlank
    private String razao_social;

    @NotBlank
    @Size(min=10, max=11)
    private String tel_contato;

    @NotNull
    @OneToOne
    @JoinColumn(name = "endereco_idendereco")
    private Endereco endereco;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_tabela_preco")
    private TabelaPreco tabelaPreco;

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getTel_contato() {
        return tel_contato;
    }

    public void setTel_contato(String tel_contato) {
        this.tel_contato = tel_contato;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TabelaPreco getTabelaPreco() {
        return tabelaPreco;
    }

    public void setTabelaPreco(TabelaPreco tabelaPreco) {
        this.tabelaPreco = tabelaPreco;
    }
}