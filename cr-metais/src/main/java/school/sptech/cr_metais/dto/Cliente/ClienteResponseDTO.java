package school.sptech.cr_metais.dto.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;

public class ClienteResponseDTO {

    @Schema(description = "Identificador da tabela", example = "1")
    private Integer idCliente;

    @Schema(description = "CNPJ do cliente", example = "42.591.651/0001-43")
    private String cnpj;

    @Schema(description = "Razão social do cliente", example = "Arcos Dourados Comércio de Alimentos SA")
    private String razaoSocial;

    @Schema(description = "Telefone de contato do cliente", example = "1132303223")
    private String telContato;

    public ClienteResponseDTO(Integer idCliente, String cnpj, String razaoSocial, String telContato) {
        this.idCliente = idCliente;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.telContato = telContato;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getTelContato() {
        return telContato;
    }

    public void setTelContato(String telContato) {
        this.telContato = telContato;
    }
}