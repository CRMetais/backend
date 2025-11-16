package school.sptech.cr_metais.dto.ContaPagamento;

import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.TipoConta;

    public class ContaPagamentoResponseDto {

        private Integer idContaPagamento;
        private Boolean pix;
        private String banco;
        private String agencia;
        private String conta;
        private TipoConta tipoConta;
        private String chavePix;
        private Boolean pertenceFornecedor;
        private String nome;
        private String documento;
        private Boolean contaAtiva;
        private Fornecedor fornecedor;

        public ContaPagamentoResponseDto() {
        }

        public ContaPagamentoResponseDto(Integer idContaPagamento, Boolean pix, String banco, String agencia,
                                         String conta, TipoConta tipoConta, String chavePix,
                                         Boolean pertenceFornecedor, String nome, String documento,
                                         Boolean contaAtiva, Fornecedor fornecedor) {
            this.idContaPagamento = idContaPagamento;
            this.pix = pix;
            this.banco = banco;
            this.agencia = agencia;
            this.conta = conta;
            this.tipoConta = tipoConta;
            this.chavePix = chavePix;
            this.pertenceFornecedor = pertenceFornecedor;
            this.nome = nome;
            this.documento = documento;
            this.contaAtiva = contaAtiva;
            this.fornecedor = fornecedor;
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

