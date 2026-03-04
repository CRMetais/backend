package school.sptech.cr_metais.entity;

import java.util.UUID;
import java.time.LocalDateTime;

public class TransacaoEtl {
    private Integer id;
    private LocalDateTime data;
    private String parceiro; // Fornecedor ou Cliente
    private String produto;
    private Double pesoKg;
    private Double precoUnitario;
    private Double valorTotalItem;
    private String tipo; // "COMPRA" ou "VENDA"
    private Double rendimento; // Ficará null para vendas

    public TransacaoEtl(Integer id, LocalDateTime data, String parceiro, String produto, Double pesoKg, Double precoUnitario, Double valorTotalItem, String tipo, Double rendimento) {
        this.id = id;
        this.data = data;
        this.parceiro = parceiro;
        this.produto = produto;
        this.pesoKg = pesoKg;
        this.precoUnitario = precoUnitario;
        this.valorTotalItem = valorTotalItem;
        this.tipo = tipo;
        this.rendimento = rendimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
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

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getValorTotalItem() {
        return valorTotalItem;
    }

    public void setValorTotalItem(Double valorTotalItem) {
        this.valorTotalItem = valorTotalItem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getRendimento() {
        return rendimento;
    }

    public void setRendimento(Double rendimento) {
        this.rendimento = rendimento;
    }
}