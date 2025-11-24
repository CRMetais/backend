package school.sptech.cr_metais.dto.Produto;

public class ProdutoRequestDto {

    private String nome;

    private String tipoProduto;

    private Double precoKg;

    private Integer idEstoque;

    public ProdutoRequestDto(String nome, String tipoProduto, Double precoKg, Integer idEstoque) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.precoKg = precoKg;
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

    public Double getPrecoKg() {
        return precoKg;
    }

    public void setPrecoKg(Double precoKg) {
        this.precoKg = precoKg;
    }

    public Integer getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Integer idEstoque) {
        this.idEstoque = idEstoque;
    }
}
