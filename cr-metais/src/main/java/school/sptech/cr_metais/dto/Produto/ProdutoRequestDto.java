package school.sptech.cr_metais.dto.Produto;

public class ProdutoRequestDto {

    private String nome;

    private String tipoProduto;

    public ProdutoRequestDto(String nome, String tipoProduto) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
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
}
