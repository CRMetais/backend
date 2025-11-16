package school.sptech.cr_metais.dto.ContaPagamento;

import jakarta.validation.constraints.NotNull;
import school.sptech.cr_metais.entity.TipoConta;


    public class  ContaPagamentoCadastroDto {

        @NotNull(message = "O campo 'pix' é obrigatório")
        private Boolean pix;

        private String banco;

        private String agencia;

        private String conta;

        private TipoConta tipoConta;

        private String chavePix;

        @NotNull
        private Boolean pertenceFornecedor;

        private String nome;

        private String documento;

        @NotNull
        private Boolean contaAtiva;

        @NotNull(message = "O id do fornecedor é obrigatório")
        private Integer idFornecedor;

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

        public Integer getIdFornecedor() {
            return idFornecedor;
        }

        public void setIdFornecedor(Integer idFornecedor) {
            this.idFornecedor = idFornecedor;
        }
    }
