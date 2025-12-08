package school.sptech.cr_metais.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cr_metais.dto.ItemPedidoVenda.ItemPedidoVendaCadastroDto;
import school.sptech.cr_metais.dto.ItemPedidoVenda.ItemPedidoVendaResponseDto;
import school.sptech.cr_metais.entity.ItemPedidoVenda;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.entity.Venda;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.ItemPedidoVendaMapper;
import school.sptech.cr_metais.repository.ItemPedidoVendaRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.repository.VendaRepository;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do ItemPedidoVendaService")
class ItemPedidoVendaServiceTest {

    @InjectMocks
    private ItemPedidoVendaService service;

    @Mock
    private ItemPedidoVendaRepository itemRepository;

    @Mock
    private ItemPedidoVendaMapper mapper;

    @Mock
    private VendaRepository vendaRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar quando venda não existir")
    void cadastrarQuandoVendaNaoExisteDeveLancarExcecaoTest() {
        ItemPedidoVendaCadastroDto dto = new ItemPedidoVendaCadastroDto();
        dto.setFk_venda(999);

        Mockito.when(mapper.toEntity(dto)).thenReturn(new ItemPedidoVenda());
        Mockito.when(vendaRepository.findById(999)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntidadeNaoEncontradaException.class,
                () -> service.cadastrar(dto));
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar quando produto não existir")
    void cadastrarQuandoProdutoNaoExisteDeveLancarExcecaoTest() {

        ItemPedidoVendaCadastroDto dto = new ItemPedidoVendaCadastroDto();
        dto.setFk_venda(1);
        dto.setFk_produto(999);

        Mockito.when(mapper.toEntity(dto)).thenReturn(new ItemPedidoVenda());
        Mockito.when(vendaRepository.findById(1)).thenReturn(Optional.of(new Venda()));
        Mockito.when(produtoRepository.findById(999)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntidadeNaoEncontradaException.class,
                () -> service.cadastrar(dto));
    }

    @Test
    @DisplayName("Deve listar itens de venda corretamente")
    void listarDeveRetornarListaTest() {

        ItemPedidoVenda item1 = new ItemPedidoVenda();
        ItemPedidoVenda item2 = new ItemPedidoVenda();

        ItemPedidoVendaResponseDto dto1 =
                new ItemPedidoVendaResponseDto();
        ItemPedidoVendaResponseDto dto2 =
                new ItemPedidoVendaResponseDto();

        List<ItemPedidoVenda> entidades = List.of(item1, item2);

        Mockito.when(itemRepository.findAll()).thenReturn(entidades);
        Mockito.when(mapper.toDto(item1)).thenReturn(dto1);
        Mockito.when(mapper.toDto(item2)).thenReturn(dto2);

        List<ItemPedidoVendaResponseDto> recebido = service.listar();

        Assertions.assertEquals(2, recebido.size());
    }


    @Test
    @DisplayName("Deve lançar exceção ao buscar item inexistente")
    void buscarPorIdInvalidoTest() {

        Mockito.when(itemRepository.findById(100))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntidadeNaoEncontradaException.class,
                () -> service.buscarPorId(100));
    }

    @Test
    @DisplayName("Deve deletar item quando ID existir")
    void deletarIdValidoTest() {
        Mockito.when(itemRepository.existsById(5)).thenReturn(true);

        service.deletar(5);

        Mockito.verify(itemRepository, Mockito.times(1)).deleteById(5);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar item inexistente")
    void deletarIdInvalidoTest() {

        Mockito.when(itemRepository.existsById(5)).thenReturn(false);

        Assertions.assertThrows(EntidadeNaoEncontradaException.class,
                () -> service.deletar(5));
    }


    @Test
    @DisplayName("Deve lançar exceção ao atualizar item inexistente")
    void atualizarItemInexistenteTest() {

        Mockito.when(itemRepository.findById(999)).thenReturn(Optional.empty());

        ItemPedidoVendaCadastroDto dto = new ItemPedidoVendaCadastroDto();

        Assertions.assertThrows(EntidadeNaoEncontradaException.class,
                () -> service.atualizar(999, dto));
    }
}
