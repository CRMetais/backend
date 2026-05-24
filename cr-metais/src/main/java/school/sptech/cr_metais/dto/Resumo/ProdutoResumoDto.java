package school.sptech.cr_metais.dto.Resumo;

public class ProdutoResumoDto {

    private String nome;
    private Double pesoComprado;
    private Double pesoVendido;
    private Double materialDisponivel;

    public ProdutoResumoDto(String nome, Double pesoComprado, Double pesoVendido, Double materialDisponivel) {
        this.nome = nome;
        this.pesoComprado = pesoComprado;
        this.pesoVendido = pesoVendido;
        this.materialDisponivel = materialDisponivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPesoComprado() {
        return pesoComprado;
    }

    public void setPesoComprado(Double pesoComprado) {
        this.pesoComprado = pesoComprado;
    }

    public Double getPesoVendido() {
        return pesoVendido;
    }

    public void setPesoVendido(Double pesoVendido) {
        this.pesoVendido = pesoVendido;
    }

    public Double getMaterialDisponivel() {
        return materialDisponivel;
    }

    public void setMaterialDisponivel(Double materialDisponivel) {
        this.materialDisponivel = materialDisponivel;
    }
}