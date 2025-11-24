package school.sptech.cr_metais.dto.Produto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProdutoResponseDto {

    @Schema(description = "Identificador do produto", example = "1")
    private Integer id;

    @Schema(description = "Nome do produto", example = "Cobre Mel")
    private String nome;

    @Schema(description = "Tipo do produto", example = "Cobre")
    private String tipoProduto;

    @Schema(description = "Identificador do estoque", example = "1")
    private Integer idEstoque;

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

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Integer getIdEstoque(){return idEstoque;}

    public void setIdEstoque(Integer idEstoque){this.idEstoque = idEstoque;}
}
