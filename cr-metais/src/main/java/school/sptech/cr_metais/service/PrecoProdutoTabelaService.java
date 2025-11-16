package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.entity.ItemPedidoCompra;
import school.sptech.cr_metais.entity.PrecoProdutoTabela;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.repository.PrecoProdutoTabelaRepository;

@Service
public class PrecoProdutoTabelaService {

    private final PrecoProdutoTabelaRepository precoProdutoTabelaRepository;

    public PrecoProdutoTabelaService(PrecoProdutoTabelaRepository precoProdutoTabelaRepository) {
        this.precoProdutoTabelaRepository = precoProdutoTabelaRepository;
    }

    public PrecoProdutoTabela criarPrecoProduto(ItemPedidoCompra itemPedidoCompra, TabelaPreco tabelaPreco) {
        PrecoProdutoTabela precoProdutoTabela = new PrecoProdutoTabela();

        precoProdutoTabela.setProduto(itemPedidoCompra.getProduto());
        precoProdutoTabela.setTabelaPreco(tabelaPreco);
        precoProdutoTabela.setPrecoProduto(itemPedidoCompra.getPrecoUnitario() * itemPedidoCompra.getPesoKg());
        return precoProdutoTabelaRepository.save(precoProdutoTabela);
    }
}
