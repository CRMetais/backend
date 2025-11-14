package school.sptech.cr_metais.dto.Fornecedor;

import io.swagger.v3.oas.annotations.media.Schema;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.TipoFornecedor;

public class FornecedorDetalhesDto {

    @Schema(description = "Nome do fornecedor")
    private final String nome;

    @Schema(description = "Número do documento do fornecedor")
    private final String documento;

    @Schema(description = "Tipo do fornecedor")
    private final TipoFornecedor tipoFornecedor;

    @Schema(description = "Telefone do fornecedor")
    private final String telefone;

    public FornecedorDetalhesDto(Fornecedor fornecedor) {
        this.nome = fornecedor.getNome();
        this.documento = fornecedor.getDocumento();
        this.tipoFornecedor = fornecedor.getTipo();
        this.telefone = fornecedor.getTelefone();
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public TipoFornecedor getTipoFornecedor() {
        return tipoFornecedor;
    }

    public String getTelefone() {
        return telefone;
    }
}
