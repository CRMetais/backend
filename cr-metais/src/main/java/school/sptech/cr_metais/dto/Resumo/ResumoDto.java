package school.sptech.cr_metais.dto.Resumo;

import java.util.List;

public class ResumoDto {

    private List<ProdutoResumoDto> produtos;
    private Double totalAplicado;
    private Double pesoTotal;
    private Double notasHoje;
    private Double pesoHoje;
    private List<ClienteResumoDto> clientes;
    private List<TabelaPrecoResumoDto> tabelasPreco;

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

    public List<ClienteResumoDto> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteResumoDto> clientes) {
        this.clientes = clientes;
    }

    public List<TabelaPrecoResumoDto> getTabelasPreco() {
        return tabelasPreco;
    }

    public void setTabelasPreco(List<TabelaPrecoResumoDto> tabelasPreco) {
        this.tabelasPreco = tabelasPreco;
    }

    public void setPesoHoje(Double pesoHoje) {
        this.pesoHoje = pesoHoje;


    }
}
