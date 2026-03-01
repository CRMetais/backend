package school.sptech.cr_metais.dto.Fornecedor;

import io.swagger.v3.oas.annotations.media.Schema;

public class FornecedorTopRendimentoDto {

    @Schema(description = "Identificador do fornecedor", example = "1")
    private Integer idFornecedor;

    @Schema(description = "Nome do fornecedor", example = "Metal Forte")
    private String nome;

    @Schema(description = "Apelido do fornecedor", example = "MF")
    private String apelido;

    @Schema(description = "Total de rendimento acumulado", example = "1520.5")
    private Double totalRendimento;

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Double getTotalRendimento() {
        return totalRendimento;
    }

    public void setTotalRendimento(Double totalRendimento) {
        this.totalRendimento = totalRendimento;
    }
}