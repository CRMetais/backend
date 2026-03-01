package school.sptech.cr_metais.dto.Produto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProdutoTopPesoVendidoDto {

    @Schema(description = "Identificador do produto", example = "1")
    private Integer idProduto;

    @Schema(description = "Nome do produto", example = "Cobre Mel")
    private String nome;

    @Schema(description = "Total de peso vendido em kg", example = "820.75")
    private Double totalPesoVendido;

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getTotalPesoVendido() {
        return totalPesoVendido;
    }

    public void setTotalPesoVendido(Double totalPesoVendido) {
        this.totalPesoVendido = totalPesoVendido;
    }
}