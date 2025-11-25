package school.sptech.cr_metais.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.sptech.cr_metais.entity.TipoTabela;

import java.time.LocalDate;

@Entity
public class TabelaPreco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tabela")
    private Integer idTabela;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoTabela tipo;

    @NotBlank
    @Column(name = "nome_tabela")
    private String nomeTabela;

    @NotNull
    @Column(name = "versao")
    private Double versao;

    @NotNull
    @Column(name = "data_inicio_validade")
    private LocalDate dataInicioValidade;


    @NotNull
    @Column(name = "data_fim_validade")
    private LocalDate dataFimValidade;

    @NotNull
    @Column(name = "ativa")
    private Boolean ativa;

    @AssertTrue(message = "Data fim não pode ser anterior à data início")
    public boolean isDatasValidas() {
        if (dataInicioValidade == null || dataFimValidade == null) {
            return true;
        }
        return !dataFimValidade.isBefore(dataInicioValidade);
    }

    public Integer getIdTabela() {
        return idTabela;
    }

    public void setIdTabela(Integer idTabela) {
        this.idTabela = idTabela;
    }

    public TipoTabela getTipo() {
        return tipo;
    }

    public void setTipo(TipoTabela tipo) {
        this.tipo = tipo;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public Double getVersao() {
        return versao;
    }

    public void setVersao(Double versao) {
        this.versao = versao;
    }

    public LocalDate getDataInicioValidade() {
        return dataInicioValidade;
    }

    public void setDataInicioValidade(LocalDate dataInicioValidade) {
        this.dataInicioValidade = dataInicioValidade;
    }

    public LocalDate getDataFimValidade() {
        return dataFimValidade;
    }

    public void setDataFimValidade(LocalDate dataFimValidade) {
        this.dataFimValidade = dataFimValidade;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }
}
