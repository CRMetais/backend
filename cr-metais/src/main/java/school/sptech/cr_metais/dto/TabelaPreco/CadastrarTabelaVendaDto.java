package school.sptech.cr_metais.dto.TabelaPreco;

import jakarta.validation.constraints.NotBlank;

public class CadastrarTabelaVendaDto {

    @NotBlank
    private String nomeTabela;

    public CadastrarTabelaVendaDto() {}

    public String getNomeTabela() { return nomeTabela; }
    public void setNomeTabela(String nomeTabela) { this.nomeTabela = nomeTabela; }
}