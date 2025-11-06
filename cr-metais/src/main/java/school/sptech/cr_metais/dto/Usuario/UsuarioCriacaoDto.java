package school.sptech.cr_metais.dto.Usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UsuarioCriacaoDto {

    @Size(min= 3, max = 32)
    @Schema(description = "Nome do usuário", example = "José Irineu")
    private String nome;

    @Email
    @Schema(description = "Email do usuário", example = "joseirineu@fe.com")
    private String email;

    @Size(min = 8, max = 32)
    @Schema(description = "Senha do usuário", example = "Teste123#")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
