package school.sptech.cr_metais.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.exception.EntidadeNulaException;
import school.sptech.cr_metais.exception.EntidadeValorAbaixoDeZeroException;
import school.sptech.cr_metais.mappers.ItemPedidoCompraMapper;
import school.sptech.cr_metais.repository.CompraRepository;
import school.sptech.cr_metais.repository.ItemPedidoCompraRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.service.ItemPedidoCompraService;

import java.util.List;
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
        @DisplayName("Deve cadastrar quando todos os cenários forem atendidos")
        void deveCadastrarComSucesso(){

            Compra compra = new Compra();
            Produto produto = new Produto();
            ItemPedidoCompra item = new ItemPedidoCompra();
            item.setIdItemPedidoCompra(20);
            item.setPesoKg(80.0);
            item.setPrecoUnitario(20.0);


            Mockito.when(compraRepository.findById(1)).thenReturn(Optional.of(compra));
            Mockito.when(produtoRepository.findById(2)).thenReturn(Optional.of(produto));
            Mockito.when(itemPedidoCompraRepository.save(item)).thenReturn(item);

            ItemPedidoCompra resultado = service.cadastrar(item, 1, 2);

            assertNotNull(resultado);
            assertEquals(20, resultado.getIdItemPedidoCompra());
            assertSame(compra, resultado.getCompra());
            assertSame(produto, resultado.getProduto());
            assertEquals(80.0, resultado.getPesoKg());
            assertEquals(20.0, resultado.getPrecoUnitario());
        }

        @Test
        @DisplayName("Deve lançar exceção se a compra não existir")
        void deveLancarExcecaoSeCompraNaoExistir(){

            ItemPedidoCompra item = new ItemPedidoCompra();
            Mockito.when(compraRepository.findById(100)).thenReturn(Optional.empty());

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> service.cadastrar(item, 100, 10),
                    "Deveria lançar exceção se a compra não existir"
            );
            assertEquals("Compra não encontrada", exception.getMessage());
        }

        @Test
        @DisplayName("Deve lançar exceção se o produto não existir")
        void deveLancarExcecaoSeProdutoNaoExistir(){

            Compra compra = new Compra();
            ItemPedidoCompra itemPedidoCompra = new ItemPedidoCompra();
            Mockito.when(compraRepository.findById(10)).thenReturn(Optional.of(compra));
            Mockito.when(produtoRepository.findById(100)).thenReturn(Optional.empty());

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> service.cadastrar(itemPedidoCompra, 10, 100),
                    "Deveria lançar exceção se o prpdito não existir"
            );
            assertEquals("Produto não encontrado", exception.getMessage());
        }

        @Test
        @DisplayName("Deve lançar excessão se Peso for nulo e não for positivo")
        void deveLancarExcecaoSePesoForZeroOuMenor(){

            ItemPedidoCompra itemPedidoCompra = new ItemPedidoCompra();
            itemPedidoCompra.setPesoKg(60.0);

            Mockito.when(compraRepository.findById(1)).thenReturn(Optional.of(new Compra()));
            Mockito.when(produtoRepository.findById(1)).thenReturn(Optional.of(new Produto()));

            itemPedidoCompra.setPrecoUnitario(null);
            assertThrows(EntidadeNulaException.class, () -> service.cadastrar(itemPedidoCompra, 1, 1));

            itemPedidoCompra.setPrecoUnitario(0.0);
            assertThrows(EntidadeValorAbaixoDeZeroException.class, () -> service.cadastrar(itemPedidoCompra, 1, 1));

            itemPedidoCompra.setPrecoUnitario(-10.0);
            assertThrows(EntidadeValorAbaixoDeZeroException.class, () -> service.cadastrar(itemPedidoCompra, 1, 1));
        }
    }

    @Nested
    @DisplayName("Método Listar")
    class MetodoListar{

        @Test
        @DisplayName("Deve retornar lista com itens")
        void deveRetornarListaComItens(){

            List<ItemPedidoCompra> listaMockada = List.of(new ItemPedidoCompra(), new ItemPedidoCompra());
            Mockito.when(itemPedidoCompraRepository.findAll()).thenReturn(listaMockada);

            List<ItemPedidoCompra> resultado = service.listar();

            assertEquals(2, resultado.size());
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando não houver itens")
        void deveRetornarListaVazia(){

            Mockito.when(itemPedidoCompraRepository.findAll()).thenReturn(List.of());

            List<ItemPedidoCompra> resultado = service.listar();

            assertTrue(resultado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Método buscarPorId")
    class MetodoBuscarPorId{

        @Test
        @DisplayName("Deve retornar item quando ele existe")
        void deveRetornarItemQuandoExiste(){

            ItemPedidoCompra itemPedidoCompra = new ItemPedidoCompra();
            Mockito.when(itemPedidoCompraRepository.findById(5)).thenReturn(Optional.of(itemPedidoCompra));

            ItemPedidoCompra resultado = service.buscarPorId(5);

            assertSame(itemPedidoCompra, resultado);
        }

        @Test
        @DisplayName("Deve lançar exceção se o item não existir")
        void deveLancarExcessaoSeItemNaoExistir(){

            Mockito.when(itemPedidoCompraRepository.findById(200)).thenReturn(Optional.empty());

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> service.buscarPorId(200),
                    "Deveria lançar exceção se o item não existir"
            );
            assertEquals("Item não encontrado", exception.getMessage());
        }

    }

    @Nested
    @DisplayName("Método deletarPorId")
    class MetodoDeletarPorId{

        @Test
        @DisplayName("Deve deletar quando o item existe")
        void deveDeletarQuandoItemExiste(){

            Mockito.when(itemPedidoCompraRepository.existsById(7)).thenReturn(true);

            service.deletarPorId(7);

            Mockito.verify(itemPedidoCompraRepository).deleteById(7);
        }

        @Test
        @DisplayName("Deve lançar exceção quando tenta deletar item que não existe")
        void deveLancarExcessaoQuandoNaoExiste(){

            Mockito.when(itemPedidoCompraRepository.existsById(77)).thenReturn(false);

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> service.deletarPorId(77),
                    "Deveria lançar exceção se o item não existir"
            );

            assertEquals("Item não encontrado", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Método atualizar")
    class MetodoAtualizar{

        @Test
        @DisplayName("Deve atualizar quando o item existe")
        void deveAtualizarQuandoExiste(){

            ItemPedidoCompra itemPedidoCompra = new ItemPedidoCompra();
            itemPedidoCompra.setIdItemPedidoCompra(21);

            Mockito.when(itemPedidoCompraRepository.existsById(21)).thenReturn(true);
            Mockito.when(itemPedidoCompraRepository.save(itemPedidoCompra)).thenReturn(itemPedidoCompra);

            ItemPedidoCompra resultado = service.atualizar(itemPedidoCompra);

            assertSame(itemPedidoCompra, resultado);
        }

        @Test
        @DisplayName("Deve lançar exceção quando tenta atualizar item que não existe")
        void deveLancarExcessaoQuandoNaoExiste(){

            ItemPedidoCompra itemPedidoCompra = new ItemPedidoCompra();
            itemPedidoCompra.setIdItemPedidoCompra(12);

            Mockito.when(itemPedidoCompraRepository.existsById(12)).thenReturn(false);

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> service.atualizar(itemPedidoCompra),
                    "Deveria lançar exceção se o item não existir"
            );

            assertEquals("Item não encontrado", exception.getMessage());
        }
    }

}
