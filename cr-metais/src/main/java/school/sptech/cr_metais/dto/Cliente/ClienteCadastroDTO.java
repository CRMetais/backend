package school.sptech.cr_metais.dto.Cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ClienteCadastroDTO {
    @NotNull(message = "CNPJ é obrigatório")
    private String cnpj;

    @NotBlank(message = "razão social é obrigatória")
    private String razaoSocial;

    @NotBlank(message = "Telefone para contato é obrigatório")
    @Size(min=10, max=11)
    private String telContato;

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
