// SalvarPrecosRequestDto.java
package school.sptech.cr_metais.dto.TabelaPreco;

import java.util.List;

public class SalvarPrecosRequestDto {

    private String nomeTabela;
    private List<ItemPrecoDto> itens;

    public SalvarPrecosRequestDto() {}

    public String getNomeTabela() { return nomeTabela; }
    public void setNomeTabela(String nomeTabela) { this.nomeTabela = nomeTabela; }

    public List<ItemPrecoDto> getItens() { return itens; }
    public void setItens(List<ItemPrecoDto> itens) { this.itens = itens; }

    public static class ItemPrecoDto {
        private String nomeProduto;
        private Double preco;

        public ItemPrecoDto() {}

        public String getNomeProduto() { return nomeProduto; }
        public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

        public Double getPreco() { return preco; }
        public void setPreco(Double preco) { this.preco = preco; }
    }
}