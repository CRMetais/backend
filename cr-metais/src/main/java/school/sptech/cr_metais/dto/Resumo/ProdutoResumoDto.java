package school.sptech.cr_metais.dto.Resumo;

public class ProdutoResumoDto {

    private String nome;
    private Double peso;
    private Double valor;
    private String destino;

    public ProdutoResumoDto(String nome, Double peso, Double valor, String destino) {
        this.nome = nome;
        this.peso = peso;
        this.valor = valor;
        this.destino = destino;
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

    public String getDestino() {
        return destino;
    }
}
