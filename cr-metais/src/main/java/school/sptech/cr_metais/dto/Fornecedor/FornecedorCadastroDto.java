package school.sptech.cr_metais.dto.Fornecedor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.cr_metais.entity.TipoFornecedor;

public class FornecedorCadastroDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String documento;

    @NotNull
    private TipoFornecedor tipoFornecedor;

    @NotBlank
    @Size(min=10, max=11)
    private String telefone;

    @NotNull
    Integer idEndereco;

    @NotNull
    Integer idTabelaPreco;

    private String apelido;
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public TipoFornecedor getTipoFornecedor() { return tipoFornecedor; }
    public void setTipoFornecedor(TipoFornecedor tipoFornecedor) { this.tipoFornecedor = tipoFornecedor; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Integer getIdTabelaPreco() {
        return idTabelaPreco;
    }

    public void setIdTabelaPreco(Integer idTabelaPreco) {
        this.idTabelaPreco = idTabelaPreco;
    }
}