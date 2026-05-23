package school.sptech.cr_metais.dto.Dashboard;

public class InfoProdutoDto {

    private String produto;
    private Integer ano;
    private Integer mes;
    private Double rendimentoTotal;
    private Double pesoTotal;

    public InfoProdutoDto(String produto, Integer ano, Integer mes,
                          Double rendimentoTotal, Double pesoTotal) {
        this.produto         = produto;
        this.ano             = ano;
        this.mes             = mes;
        this.rendimentoTotal = rendimentoTotal;
        this.pesoTotal       = pesoTotal;
    }

    public String getProduto()         { return produto; }
    public Integer getAno()            { return ano; }
    public Integer getMes()            { return mes; }
    public Double getRendimentoTotal() { return rendimentoTotal; }
    public Double getPesoTotal()       { return pesoTotal; }
}

