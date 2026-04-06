package school.sptech.cr_metais.dto.Cliente;

public class TabelaClienteResponseDTO {
    private Integer idCliente;
    private String razaoSocial;
    private String cnpj;
    private String tabelaPreco;

    public TabelaClienteResponseDTO(Integer idCliente, String razaoSocial, String cnpj, String tabelaPreco) {
        this.idCliente = idCliente;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.tabelaPreco = tabelaPreco;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTabelaPreco() {
        return tabelaPreco;
    }

    public void setTabelaPreco(String tabelaPreco) {
        this.tabelaPreco = tabelaPreco;
    }
}
