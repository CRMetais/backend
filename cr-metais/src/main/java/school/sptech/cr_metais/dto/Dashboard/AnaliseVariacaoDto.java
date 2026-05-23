package school.sptech.cr_metais.dto.Dashboard;

public class AnaliseVariacaoDto {

    private String mes;
    private Double mediaPreco;
    private Double variacaoPercentual; // null no primeiro mês

    public AnaliseVariacaoDto(String mes, Double mediaPreco, Double variacaoPercentual) {
        this.mes                = mes;
        this.mediaPreco         = mediaPreco;
        this.variacaoPercentual = variacaoPercentual;
    }

    public String getMes()                 { return mes; }
    public Double getMediaPreco()          { return mediaPreco; }
    public Double getVariacaoPercentual()  { return variacaoPercentual; }
}