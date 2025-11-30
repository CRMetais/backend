package school.sptech.cr_metais.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cr_metais.entity.Estoque;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.ProdutoMapper;
import school.sptech.cr_metais.repository.EstoqueRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes unitários do ProdutoService")
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private EstoqueRepository estoqueRepository;

    @Mock
    private ProdutoMapper produtoMapper;

    @Test
    @DisplayName("Deve retornar lista vazia ao listar produtos")
    void listarDeveRetornarListaVaziaTeste() {
        List<Produto> produtos = new ArrayList<>();
        Mockito.when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> recebido = produtoService.listar();

        Assertions.assertNotNull(recebido);
        Assertions.assertTrue(recebido.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar três produtos ao listar")
    void listarDeveRetornarTresProdutosTeste() {
        List<Produto> produtos = List.of(new Produto(), new Produto(), new Produto());
        Mockito.when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> recebido = produtoService.listar();

        Assertions.assertEquals(3, recebido.size());
    }

    @Test
    @DisplayName("Deve retornar produto ao buscar por ID válido")
    void buscarPorIdQuandoIdValidoDeveRetornarProdutoTeste() {
        Produto produto = new Produto();
        produto.setIdProduto(1);
        produto.setNome("Cobre");

        Mockito.when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));

        Produto recebido = produtoService.buscarPorId(1);

        Assertions.assertNotNull(recebido);
        Assertions.assertEquals("Cobre", recebido.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar produto com ID inválido")
    void buscarPorIdQuandoIdInvalidoDeveLancarExcecaoTeste() {
        Mockito.when(produtoRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntidadeNaoEncontradaException.class,
                () -> produtoService.buscarPorId(99));
    }

    @Test
    @DisplayName("Deve deletar produto ao fornecer ID válido")
    void deletarQuandoIdValidoDeveExecutarDeleteTest() {
        Mockito.when(produtoRepository.existsById(10)).thenReturn(true);

        produtoService.deletar(10);

        Mockito.verify(produtoRepository, Mockito.times(1)).deleteById(10);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar produto inexistente")
    void deletarQuandoIdInvalidoDeveLancarExcecaoTest() {
        Mockito.when(produtoRepository.existsById(10)).thenReturn(false);

        Assertions.assertThrows(EntidadeNaoEncontradaException.class,
                () -> produtoService.deletar(10));
    }

    @Test
    @DisplayName("Deve cadastrar produto quando estoque é válido")
    void cadastrarQuandoEstoqueValidoDeveCadastrarProdutoTest() {
        Estoque estoque = new Estoque();
        estoque.setIdEstoque(1);

        Produto produto = new Produto();
        produto.setNome("Alumínio");

        Mockito.when(estoqueRepository.findById(1)).thenReturn(Optional.of(estoque));
        Mockito.when(produtoRepository.save(produto)).thenReturn(produto);

        Produto recebido = produtoService.cadastrar(produto, 1);

        Assertions.assertNotNull(recebido);
        Assertions.assertEquals("Alumínio", recebido.getNome());
        Assertions.assertEquals(estoque, recebido.getEstoque());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar produto com estoque inexistente")
    void cadastrarQuandoEstoqueInvalidoDeveLancarExcecaoTest() {
        Produto produto = new Produto();
        produto.setNome("Ferro");

        Mockito.when(estoqueRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntidadeNaoEncontradaException.class,
                () -> produtoService.cadastrar(produto, 99));
    }

    @Test
    @DisplayName("Deve atualizar produto existente com sucesso")
    void atualizarQuandoProdutoExisteDeveSalvarTest() {
        Produto produto = new Produto();
        produto.setIdProduto(5);
        produto.setNome("Chumbo");

        Mockito.when(produtoRepository.existsById(5)).thenReturn(true);
        Mockito.when(produtoRepository.save(produto)).thenReturn(produto);

        Produto atualizado = produtoService.atualizar(produto);

        Assertions.assertEquals("Chumbo", atualizado.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar produto inexistente")
    void atualizarQuandoProdutoNaoExisteDeveLancarExcecaoTest() {
        Produto produto = new Produto();
        produto.setIdProduto(5);

        Mockito.when(produtoRepository.existsById(5)).thenReturn(false);

        Assertions.assertThrows(EntidadeNaoEncontradaException.class,
                () -> produtoService.atualizar(produto));
    }
}
