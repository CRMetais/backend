package school.sptech.cr_metais.dto.TabelaPreco;

import jakarta.validation.constraints.*;
import school.sptech.cr_metais.entity.TipoTabela;

import java.time.LocalDate;

public class TabelaPrecoCadastroDTO {
    @NotNull(message = "Tipo é obrigatório")
    TipoTabela tipo;

    @NotBlank(message = "Nome da tabela é obrigatório")
    String nomeTabela;

    @NotNull(message = "Versão é obrigatória")
    Double versao;

    LocalDate dataInicioValidade;
    LocalDate dataFimValidade;

    Boolean ativa;

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