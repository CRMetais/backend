package school.sptech.cr_metais.dto.Usuario;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RascunhoColaboradorDto {
    private String nome;
    private String email;
    private String cargo;

    // CONSTRUTOR PADRÃO EXPLICÍTO (Obrigatório para o Jackson)
    public RascunhoColaboradorDto() {
    }

    public String getNome()               { return nome; }
    public void setNome(String nome)      { this.nome = nome; }

    public String getEmail()              { return email; }
    public void setEmail(String email)    { this.email = email; }

    public String getCargo()              { return cargo; }
    public void setCargo(String cargo)    { this.cargo = cargo; }
}