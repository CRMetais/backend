package school.sptech.cr_metais.dto.Dashboard;

public class InfoFornecedorDto {

    private String nomeFornecedor;
    private Integer ano;
    private Integer mes;
    private Double pesoTotal;
    private Double rendimentoTotal;

    public InfoFornecedorDto(String nomeFornecedor, Integer ano, Integer mes,
                             Double pesoTotal, Double rendimentoTotal) {
        this.nomeFornecedor = nomeFornecedor;
        this.ano            = ano;
        this.mes            = mes;
        this.pesoTotal      = pesoTotal;
        this.rendimentoTotal = rendimentoTotal;
    }

    public String getNomeFornecedor()  { return nomeFornecedor; }
    public Integer getAno()            { return ano; }
    public Integer getMes()            { return mes; }
    public Double getPesoTotal()       { return pesoTotal; }
    public Double getRendimentoTotal() { return rendimentoTotal; }
}

