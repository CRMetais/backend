package school.sptech.cr_metais.dto.Historico;

import java.time.LocalDate;

public class TransacaoHistoricoDto {
    private Integer id;
    private LocalDate data;
    private String parceiro;
    private String produto;
    private Double peso;
    private Double preco;
    private Double total;
    private Double rendimento;
    private String tipo;

    public TransacaoHistoricoDto(Integer id, LocalDate data, String parceiro, String produto,
                                 Double peso, Double preco, Double total,
                                 Double rendimento, String tipo) {
        this.id = id;
        this.data = data;
        this.parceiro = parceiro;
        this.produto = produto;
        this.peso = peso;
        this.preco = preco;
        this.total = total;
        this.rendimento = rendimento;
        this.tipo = tipo;
    }

    // getters
    public Integer getId() { return id; }
    public LocalDate getData() { return data; }
    public String getParceiro() { return parceiro; }
    public String getProduto() { return produto; }
    public Double getPeso() { return peso; }
    public Double getPreco() { return preco; }
    public Double getTotal() { return total; }
    public Double getRendimento() { return rendimento; }
    public String getTipo() { return tipo; }
}
