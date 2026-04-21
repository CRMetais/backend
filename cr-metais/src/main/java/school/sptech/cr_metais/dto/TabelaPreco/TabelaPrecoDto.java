package school.sptech.cr_metais.dto.TabelaPreco;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class TabelaPrecoDto {

    private String nomeTabela;
    private LocalDate dataInicioValidade;
    private Double precoProduto;
    private Double versao;

    public TabelaPrecoDto() {}

    public TabelaPrecoDto(String nomeTabela, LocalDate dataInicioValidade, Double precoProduto, Double versao) {
        this.nomeTabela = nomeTabela;
        this.dataInicioValidade = dataInicioValidade;
        this.precoProduto = precoProduto;
        this.versao = versao;
    }

    public String getNomeTabela() { return nomeTabela; }
    public void setNomeTabela(String nomeTabela) { this.nomeTabela = nomeTabela; }

    public LocalDate getDataInicioValidade() { return dataInicioValidade; }
    public void setDataInicioValidade(LocalDate dataInicioValidade) { this.dataInicioValidade = dataInicioValidade; }

    public Double getPrecoProduto() { return precoProduto; }
    public void setPrecoProduto(Double precoProduto) { this.precoProduto = precoProduto; }

    public Double getVersao() { return versao; }
    public void setVersao(Double versao) { this.versao = versao; }
}