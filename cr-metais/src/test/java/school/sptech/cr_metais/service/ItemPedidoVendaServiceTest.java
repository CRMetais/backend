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
    @DisplayName("Deve cadastrar item quando venda e produto existirem")
    void cadastrarQuandoTudoValidoDeveCadastrarTest() {

        ItemPedidoVendaCadastroDto dto = new ItemPedidoVendaCadastroDto();
        dto.setFk_venda(1);
        dto.setFk_produto(10);
        dto.setPesoKg(20.0);
        dto.setPrecoUnitario(9.5);

        ItemPedidoVenda entidade = new ItemPedidoVenda();

        Venda venda = new Venda();
        venda.setIdVenda(1);

        Produto produto = new Produto();
        produto.setIdProduto(10);

        ItemPedidoVendaResponseDto resposta = new ItemPedidoVendaResponseDto(
                50, venda, produto, 20.0, 9.5
        );

        Mockito.when(mapper.toEntity(dto)).thenReturn(entidade);
        Mockito.when(vendaRepository.findById(1)).thenReturn(Optional.of(venda));
        Mockito.when(produtoRepository.findById(10)).thenReturn(Optional.of(produto));
        Mockito.when(itemRepository.save(entidade)).thenReturn(entidade);
        Mockito.when(mapper.toDto(entidade)).thenReturn(resposta);

        ItemPedidoVendaResponseDto recebido = service.cadastrar(dto);

        Assertions.assertNotNull(recebido);
        Assertions.assertEquals(50, recebido.getIdItemVenda());
        Assertions.assertEquals(20.0, recebido.getPesoKg());
    }

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
                new ItemPedidoVendaResponseDto(1, null, null, 10.0, 5.0);
        ItemPedidoVendaResponseDto dto2 =
                new ItemPedidoVendaResponseDto(2, null, null, 20.0, 9.0);

        List<ItemPedidoVenda> entidades = List.of(item1, item2);

        Mockito.when(itemRepository.findAll()).thenReturn(entidades);
        Mockito.when(mapper.toDto(item1)).thenReturn(dto1);
        Mockito.when(mapper.toDto(item2)).thenReturn(dto2);

        List<ItemPedidoVendaResponseDto> recebido = service.listar();

        Assertions.assertEquals(2, recebido.size());
    }

    @Test
    @DisplayName("Deve buscar item por ID corretamente")
    void buscarPorIdValidoTest() {

        ItemPedidoVenda item = new ItemPedidoVenda();
        item.setIdItemVenda(7);

        ItemPedidoVendaResponseDto dto =
                new ItemPedidoVendaResponseDto(7, null, null, 15.0, 6.0);

        Mockito.when(itemRepository.findById(7)).thenReturn(Optional.of(item));
        Mockito.when(mapper.toDto(item)).thenReturn(dto);

        ItemPedidoVendaResponseDto recebido = service.buscarPorId(7);

        Assertions.assertEquals(7, recebido.getIdItemVenda());
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
    @DisplayName("Deve atualizar item com sucesso")
    void atualizarItemValidoTest() {

        Integer id = 10;

        ItemPedidoVenda existente = new ItemPedidoVenda();
        existente.setIdItemVenda(id);

        ItemPedidoVendaCadastroDto dto = new ItemPedidoVendaCadastroDto();
        dto.setFk_venda(1);
        dto.setFk_produto(10);
        dto.setPesoKg(88.0);
        dto.setPrecoUnitario(12.5);

        Venda venda = new Venda();
        venda.setIdVenda(1);

        Produto produto = new Produto();
        produto.setIdProduto(10);

        ItemPedidoVenda atualizado = new ItemPedidoVenda();
        atualizado.setIdItemVenda(id);
        atualizado.setPesoKg(88.0);
        atualizado.setPrecoUnitario(12.5);

        ItemPedidoVendaResponseDto resposta =
                new ItemPedidoVendaResponseDto(id, venda, produto, 88.0, 12.5);

        Mockito.when(itemRepository.findById(id)).thenReturn(Optional.of(existente));
        Mockito.when(vendaRepository.findById(1)).thenReturn(Optional.of(venda));
        Mockito.when(produtoRepository.findById(10)).thenReturn(Optional.of(produto));
        Mockito.when(itemRepository.save(existente)).thenReturn(atualizado);
        Mockito.when(mapper.toDto(atualizado)).thenReturn(resposta);

        ItemPedidoVendaResponseDto recebido = service.atualizar(id, dto);

        Assertions.assertEquals(id, recebido.getIdItemVenda());
        Assertions.assertEquals(88.0, recebido.getPesoKg());
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
