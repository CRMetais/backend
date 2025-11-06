package school.sptech.cr_metais.dto.Usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioLoginDto {

    @Schema(description = "Email do usuário", example = "joseirineu@fe.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "Teste123#")
    private String senha;

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
