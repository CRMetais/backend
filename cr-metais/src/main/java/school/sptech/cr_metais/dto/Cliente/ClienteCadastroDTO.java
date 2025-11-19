package school.sptech.cr_metais.dto.Cliente;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class ClienteCadastroDTO {
    @NotNull(message = "CNPJ é obrigatório")
    private String cnpj;

    @NotBlank(message = "razão social é obrigatória")
    private String razao_social;

    @NotBlank(message = "Telefone para contato é obrigatório")
    @Size(min=10, max=11)
    private String tel_contato;

    @NotNull(message = "Endereço é obrigatório")
    private Integer idEndereco;

    @NotNull(message = "Tabela de preço é obrigatório")
    private Integer idTabelaPreco;

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

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Integer getIdTabelaPreco() {
        return idTabelaPreco;
    }

    public void setIdTabelaPreco(Integer idTabelaPreco) {
        this.idTabelaPreco = idTabelaPreco;
    }
}
