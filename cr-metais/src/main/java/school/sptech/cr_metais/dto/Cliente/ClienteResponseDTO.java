package school.sptech.cr_metais.dto.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;

public class ClienteResponseDTO {

    @Schema(description = "Identificador da tabela", example = "1")
    private Integer id_cliente;

    @Schema(description = "CNPJ do cliente", example = "42.591.651/0001-43")
    private String cnpj;

    @Schema(description = "Razão social do cliente", example = "Arcos Dourados Comércio de Alimentos SA")
    private String razao_social;

    @Schema(description = "Telefone de contato do cliente", example = "1132303223")
    private String tel_contato;

    public ClienteResponseDTO(Integer id_cliente, String cnpj, String razao_social, String tel_contato) {
        this.id_cliente = id_cliente;
        this.cnpj = cnpj;
        this.razao_social = razao_social;
        this.tel_contato = tel_contato;
    }

    public ClienteResponseDTO() {
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getTel_contato() {
        return tel_contato;
    }

    public void setTel_contato(String tel_contato) {
        this.tel_contato = tel_contato;
    }
}