package school.sptech.cr_metais.dto.Produto;

public class ProdutoRequestDto {

    private String nome;

    private String tipoProduto;

    private Integer idEstoque;

    public ProdutoRequestDto(String nome, String tipoProduto, Integer idEstoque) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.idEstoque = idEstoque;
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

    public Integer getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Integer idEstoque) {
        this.idEstoque = idEstoque;
    }
}
