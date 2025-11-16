package school.sptech.cr_metais.dto.TabelaPreco;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.cr_metais.entity.TipoTabela;

import java.time.LocalDate;

public class TabelaPrecoResponseDTO {

    @Schema(description = "Identificador da tabela", example = "1")
    Integer idTabela;

    @Schema(description = "Nome da tabela", example = "Padrão")
    String nomeTabela;

    @Schema(description = "Tipo da tabela (Compra ou venda)", example = "C")
    TipoTabela tipo;

    @Schema(description = "Versão da tabela", example = "1.0")
    Double versao;

    @Schema(description = "Data de início da tabela", example = "2025-11-15")
    LocalDate dataInicioValidade;

    @Schema(description = "Data que a tabela deixou de ser ativa", example = "2025-11-15")
    LocalDate dataFimValidade;

    @Schema(description = "Define se a tabela está ativa", example = "true")
    Boolean ativa;

    public TabelaPrecoResponseDTO(Integer idTabela, String nomeTabela, TipoTabela tipo, Double versao, LocalDate dataInicioValidade, LocalDate dataFimValidade, Boolean ativa) {
        this.idTabela = idTabela;
        this.nomeTabela = nomeTabela;
        this.tipo = tipo;
        this.versao = versao;
        this.dataInicioValidade = dataInicioValidade;
        this.dataFimValidade = dataFimValidade;
        this.ativa = ativa;
    }

    public TabelaPrecoResponseDTO() {
    }

    public Integer getIdTabela() {
        return idTabela;
    }

    public void setIdTabela(Integer idTabela) {
        this.idTabela = idTabela;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public TipoTabela getTipo() {
        return tipo;
    }

    public void setTipo(TipoTabela tipo) {
        this.tipo = tipo;
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
