package school.sptech.cr_metais.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class ContaPagamento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idconta_pagamento")
    private Integer idContaPagamento;

    @Column(name = "pix")
    @NotNull
    private Boolean pix;

    @Column(name = "banco", length = 45)
    private String banco;

    @Column(name = "agencia", length = 45)
    private String agencia;

    @Column(name = "conta", length = 45)
    private String conta;

    @Column(name = "tipo_conta", length = 1)
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;


    @Column(name = "chave_pix", length = 45)
    private String chavePix;

    @Column(name = "pertence_fornecedor")
    @NotNull
    private Boolean pertenceFornecedor;

    @Column(name = "nome", length = 200)
    private String nome;

    @Column(name = "documento", length = 14)
    private String documento;

    @Column(name = "conta_ativa")
    @NotNull
    private Boolean contaAtiva;

    @ManyToOne
    @JoinColumn(name = "fk_fornecedor")
    private Fornecedor fornecedor;

    @AssertTrue(message = "É necessário ter uma conta ou chave pix")
    public boolean isValido() {
        return !(chavePix == null && conta == null);
    }

    public Integer getIdContaPagamento() {
        return idContaPagamento;
    }

    public void setIdContaPagamento(Integer idContaPagamento) {
        this.idContaPagamento = idContaPagamento;
    }

    public Boolean getPix() {
        return pix;
    }

    public void setPix(Boolean pix) {
        this.pix = pix;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public Boolean getPertenceFornecedor() {
        return pertenceFornecedor;
    }

    public void setPertenceFornecedor(Boolean pertenceFornecedor) {
        this.pertenceFornecedor = pertenceFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Boolean getContaAtiva() {
        return contaAtiva;
    }

    public void setContaAtiva(Boolean contaAtiva) {
        this.contaAtiva = contaAtiva;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}


