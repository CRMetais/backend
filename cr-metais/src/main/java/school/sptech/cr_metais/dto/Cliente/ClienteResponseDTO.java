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

    // Construtor com os nomes e ordens corretas
    public ClienteResponseDTO(Integer idCliente, String razaoSocial, String cnpj, String telContato) {
        this.idCliente = idCliente;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.telContato = telContato;
    }

    // Getters e Setters atualizados
    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getTelContato() { return telContato; }
    public void setTelContato(String telContato) { this.telContato = telContato; }
}