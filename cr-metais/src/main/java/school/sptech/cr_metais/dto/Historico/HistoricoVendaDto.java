package school.sptech.cr_metais.dto.Historico;

import java.time.LocalDate;

public class HistoricoVendaDto {

    private Integer id;
    private LocalDate data;
    private String parceiro;
    private String produto;
    private Double peso;
    private Double preco;
    private Double total;
    private Double rendimento;
    private String tipo;

    // Construtor vazio
    public HistoricoVendaDto() {
    }

    // Construtor completo
    public HistoricoVendaDto(Integer id, LocalDate data, String parceiro, String produto,
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

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getParceiro() {
        return parceiro;
    }

    public void setParceiro(String parceiro) {
        this.parceiro = parceiro;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getRendimento() {
        return rendimento;
    }

    public void setRendimento(Double rendimento) {
        this.rendimento = rendimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}