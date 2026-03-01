package school.sptech.cr_metais.dto.TabelaPreco;

import io.swagger.v3.oas.annotations.media.Schema;

public class FornecedorTabelaPrecoDto {

    @Schema(description = "Identificador do fornecedor", example = "1")
    private Integer idFornecedor;

    @Schema(description = "Nome do fornecedor", example = "Carlos Eduardo Mendes")
    private String nome;

    @Schema(description = "Apelido do fornecedor", example = "Carlos")
    private String apelido;

    @Schema(description = "Documento do fornecedor", example = "12345678901")
    private String documento;

    @Schema(description = "Telefone do fornecedor", example = "11991234567")
    private String telefone;

    @Schema(description = "Identificador da tabela de preço vinculada", example = "1")
    private Integer fkTabelaPreco;

    @Schema(description = "Nome da tabela de preço", example = "Padrão")
    private String nomeTabela;

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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getFkTabelaPreco() {
        return fkTabelaPreco;
    }

    public void setFkTabelaPreco(Integer fkTabelaPreco) {
        this.fkTabelaPreco = fkTabelaPreco;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }
}