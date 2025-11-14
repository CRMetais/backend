package school.sptech.cr_metais.dto.Fornecedor;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.cr_metais.entity.TipoFornecedor;

public class FornecedorListarDto {

    @Schema(description = "Identificador do fornecedor", example = "1")
    private Integer id;
    @Schema(description = "Nome do fornecedor", examples = {"Ronaldo Nazário","Fênix metais"})
    private String nome;
    @Schema(description = "Apelido do fornecedor",examples = "Naldinho")
    private String apelido;
    @Schema(description = "Tipo do fornecedor", examples = "")
    private TipoFornecedor tipoFornecedor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TipoFornecedor getTipoFornecedor() {
        return tipoFornecedor;
    }

    public void setTipoFornecedor(TipoFornecedor tipoFornecedor) {
        this.tipoFornecedor = tipoFornecedor;
    }
}
