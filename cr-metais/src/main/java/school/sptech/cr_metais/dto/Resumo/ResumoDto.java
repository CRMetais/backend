package school.sptech.cr_metais.dto.Resumo;

import java.util.List;

public class ResumoDto {

    private List<ProdutoResumoDto> produtos;
    private Double totalAplicado;
    private Double pesoTotal;
    private Double notasHoje;
    private Double pesoHoje;

    public List<ProdutoResumoDto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoResumoDto> produtos) {
        this.produtos = produtos;
    }

    public Double getTotalAplicado() {
        return totalAplicado;
    }

    public void setTotalAplicado(Double totalAplicado) {
        this.totalAplicado = totalAplicado;
    }

    public Double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(Double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public Double getNotasHoje() {
        return notasHoje;
    }

    public void setNotasHoje(Double notasHoje) {
        this.notasHoje = notasHoje;
    }

    public Double getPesoHoje() {
        return pesoHoje;
    }

    public void setPesoHoje(Double pesoHoje) {
        this.pesoHoje = pesoHoje;
    }
}
