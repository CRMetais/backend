package school.sptech.cr_metais.controller;

import org.springframework.web.bind.annotation.*;
import school.sptech.cr_metais.entity.ItemPedidoCompra;
import school.sptech.cr_metais.entity.PrecoProdutoTabela;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.service.PrecoProdutoTabelaService;

@RestController
@RequestMapping("/precos-produto")
public class PrecoProdutoTabelaController {

    private final PrecoProdutoTabelaService precoProdutoTabelaService;

    public PrecoProdutoTabelaController(PrecoProdutoTabelaService precoProdutoTabelaService) {
        this.precoProdutoTabelaService = precoProdutoTabelaService;
    }

    @PostMapping("/criar")
    public PrecoProdutoTabela criarPrecoProduto(
            @RequestBody PrecoProdutoRequest request
    ) {
        ItemPedidoCompra itemPedidoCompra = request.getItemPedidoCompra();
        TabelaPreco tabelaPreco = request.getTabelaPreco();

        return precoProdutoTabelaService.criarPrecoProduto(itemPedidoCompra, tabelaPreco);
    }

    public static class PrecoProdutoRequest {
        private ItemPedidoCompra itemPedidoCompra;
        private TabelaPreco tabelaPreco;

        public ItemPedidoCompra getItemPedidoCompra() {
            return itemPedidoCompra;
        }

        public void setItemPedidoCompra(ItemPedidoCompra itemPedidoCompra) {
            this.itemPedidoCompra = itemPedidoCompra;
        }

        public TabelaPreco getTabelaPreco() {
            return tabelaPreco;
        }

        public void setTabelaPreco(TabelaPreco tabelaPreco) {
            this.tabelaPreco = tabelaPreco;
        }
    }
}
