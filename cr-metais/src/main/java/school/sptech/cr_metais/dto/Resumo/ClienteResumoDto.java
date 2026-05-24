package school.sptech.cr_metais.dto.Resumo;

public class ClienteResumoDto {

    private String nome;

    public ClienteResumoDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}