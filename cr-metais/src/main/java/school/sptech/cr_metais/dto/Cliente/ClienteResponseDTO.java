package school.sptech.cr_metais.dto.Cliente;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClienteResponseDTO {

    private Integer idCliente;
    @Schema(description = "Razão social do cliente", example = "Vital Metalúrgica")
    private String razaoSocial;
    @Schema(description = "CNPJ do cliente", example = "43758362000100")
    private String cnpj;
    @Schema(description = "Telefone de contato do cliente", example = "11977773333")
    private String telContato;
    @Schema(description = "Inscrição Estadual do cliente", example = "123123123123")
    private String ie;

    // Sub-DTOs embutidos para evitar expor as entities diretamente
    private TabelaPrecoResDTO tabelaPreco;
    private EnderecoResDTO endereco;

    // Construtor completo
    public ClienteResponseDTO(Integer idCliente, String razaoSocial, String cnpj, String telContato,
                              TabelaPrecoResDTO tabelaPreco, EnderecoResDTO endereco, String ie) {
        this.idCliente = idCliente;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.telContato = telContato;
        this.tabelaPreco = tabelaPreco;
        this.endereco = endereco;
        this.ie = ie;
    }

    // Getters e Setters
    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getTelContato() { return telContato; }
    public void setTelContato(String telContato) { this.telContato = telContato; }
    public TabelaPrecoResDTO getTabelaPreco() { return tabelaPreco; }
    public void setTabelaPreco(TabelaPrecoResDTO tabelaPreco) { this.tabelaPreco = tabelaPreco; }
    public EnderecoResDTO getEndereco() { return endereco; }
    public void setEndereco(EnderecoResDTO endereco) { this.endereco = endereco; }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    // --- Sub-classes DTO para isolamento da camada ---
    public static class TabelaPrecoResDTO {
        private Integer idTabela;
        private String nomeTabela;
        private String tipo;
        private Double versao;

        public TabelaPrecoResDTO(Integer idTabela, String nomeTabela, String tipo, Double versao) {
            this.idTabela = idTabela;
            this.nomeTabela = nomeTabela;
            this.tipo = tipo;
            this.versao = versao;
        }
        public Integer getIdTabela() { return idTabela; }
        public String getNomeTabela() { return nomeTabela; }
        public String getTipo() { return tipo; }
        public Double getVersao() { return versao; }
    }

    public static class EnderecoResDTO {
        private Integer idEndereco;
        private String logradouro;
        private String numero;
        private String bairro;
        private String cidade;
        private String estado;
        private String cep;
        private String complemento;

        public EnderecoResDTO(Integer idEndereco, String logradouro, String numero, String bairro,
                              String cidade, String estado, String cep, String complemento) {
            this.idEndereco = idEndereco;
            this.logradouro = logradouro;
            this.numero = numero;
            this.bairro = bairro;
            this.cidade = cidade;
            this.estado = estado;
            this.cep = cep;
            this.complemento = complemento;
        }
        public Integer getIdEndereco() { return idEndereco; }
        public String getLogradouro() { return logradouro; }
        public String getNumero() { return numero; }
        public String getBairro() { return bairro; }
        public String getCidade() { return cidade; }
        public String getEstado() { return estado; }
        public String getCep() { return cep; }
        public String getComplemento() { return complemento; }
    }
}