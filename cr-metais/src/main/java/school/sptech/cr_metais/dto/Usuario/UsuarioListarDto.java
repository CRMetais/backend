package school.sptech.cr_metais.dto.Usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioListarDto {

    @Schema(description = "Id do usuário", example = "1")
    private Integer idUsuario;
    @Schema(description = "Nome do usuário", example = "José Irineu")
    private String nome;
    @Schema(description = "Email do usuário", example = "joseirineu@fe.com")
    private String email;
    @Schema(description = "Cargo do usuário", example = "ADMIN")
    private String cargo;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

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
}
