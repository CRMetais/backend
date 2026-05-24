package school.sptech.cr_metais.dto.Resumo;

public class TabelaPrecoResumoDto {

    private String nomeTabela;
    private String nomeProduto;
    private Double precoProduto;

    public TabelaPrecoResumoDto(String nomeTabela, String nomeProduto, Double precoProduto) {
        this.nomeTabela = nomeTabela;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }
}