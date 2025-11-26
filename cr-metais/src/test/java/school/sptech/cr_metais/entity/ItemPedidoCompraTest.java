package school.sptech.cr_metais.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cr_metais.mappers.ItemPedidoCompraMapper;
import school.sptech.cr_metais.repository.CompraRepository;
import school.sptech.cr_metais.repository.ItemPedidoCompraRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.service.ItemPedidoCompraService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
@DisplayName("Testes da ItemPedidoCompra")
public class ItemPedidoCompraTest {

    @Mock
    private ItemPedidoCompraRepository itemPedidoCompraRepository;

    @Mock
    private CompraRepository compraRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ItemPedidoCompraMapper mapper;

    @InjectMocks
    private ItemPedidoCompraService service;

    @Nested
    @DisplayName("Método Cadastrar")
    class MetodoCadastrar{

        @Test
        @DisplayName("Deve cadastrar quando compra e produto existirem")
        void deveCadastrarComSucesso(){

            Integer idCompra = 1;
            Integer idProduto = 2;
            Compra compra = new Compra();
            Produto produto = new Produto();
            ItemPedidoCompra item = new ItemPedidoCompra();
            item.setId(20);

            Mockito.when(compraRepository.findById(1)).thenReturn(Optional.of(compra));
            Mockito.when(produtoRepository.findById(2)).thenReturn(Optional.of(produto));
            Mockito.when(itemPedidoCompraRepository.save(item)).thenReturn(item);

            ItemPedidoCompra resultado = service.cadastrar(item, 1, 2);

            assertNotNull(resultado);
            assertEquals(20, resultado.getId());
            assertSame(compra, resultado.getCompra());
            assertSame(produto, resultado.getProduto());
        }
    }
}
