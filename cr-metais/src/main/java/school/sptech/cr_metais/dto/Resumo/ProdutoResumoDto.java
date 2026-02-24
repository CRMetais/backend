package school.sptech.cr_metais.dto.Resumo;

public class ProdutoResumoDto {

    private String nome;
    private Double peso;
    private Double valor;
    private Double total;

    public ProdutoResumoDto(String nome, Double peso, Double valor, Double total) {
        this.nome = nome;
        this.peso = peso;
        this.valor = valor;
        this.total = total;
    }

    public String getNome() {
        return nome;
    }

    public Double getPeso() {
        return peso;
    }

    public Double getValor() {
        return valor;
    }

    public Double getTotal() {
        return total;
    }
}
