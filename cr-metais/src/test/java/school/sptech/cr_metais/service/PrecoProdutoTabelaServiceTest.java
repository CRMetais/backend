package school.sptech.cr_metais.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.cr_metais.entity.*;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.exception.EntidadeNulaException;
import school.sptech.cr_metais.exception.EntidadeValorAbaixoDeZeroException;
import school.sptech.cr_metais.mappers.PrecoProdutoTabelaMapper;
import school.sptech.cr_metais.repository.PrecoProdutoTabelaRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
@DisplayName("Testes PrecoProdutoTabelaService")
class PrecoProdutoTabelaServiceTest {


    @Mock
    private PrecoProdutoTabelaRepository precoProdutoTabelaRepository;

    @Mock
    private TabelaPrecoRepository tabelaPrecoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private PrecoProdutoTabelaMapper precoProdutoTabelaMapper;

    @InjectMocks
    private PrecoProdutoTabelaService precoProdutoTabelaService;

    @Nested
    @DisplayName("Metodo Cadastrar")
    class MetodoCadastrar{

        @Test
        @DisplayName("Deve cadastrar corretamente quando todos os cenários forem atendidos")
        void deveCadastrarCorretamente(){
            TabelaPreco tabelaPreco = new TabelaPreco();
            Produto produto = new Produto();
            PrecoProdutoTabela precoProdutoTabela = new PrecoProdutoTabela();
            precoProdutoTabela.setIdPrecoProdutoTabela(1);
            precoProdutoTabela.setPrecoProduto(20.0);

            when(tabelaPrecoRepository.findById(1)).thenReturn(Optional.of(tabelaPreco));
            when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));
            when(precoProdutoTabelaRepository.save(precoProdutoTabela)).thenReturn(precoProdutoTabela);

            PrecoProdutoTabela resultado = precoProdutoTabelaService.cadastrar(precoProdutoTabela, 1, 1);

            assertNotNull(resultado);
            assertEquals(1, resultado.getIdPrecoProdutoTabela());
            assertSame(tabelaPreco, resultado.getTabelaPreco());
            assertSame(produto, resultado.getProduto());
            assertEquals(20.0, resultado.getPrecoProduto());
        }

        @Test
        @DisplayName("Deve lançar exceção se a TabelaPreco não existir")
        void deveLancarExcecaoSeTabelaPrecoNaoExistir(){

            PrecoProdutoTabela precoProdutoTabela = new PrecoProdutoTabela();
            when(tabelaPrecoRepository.findById(10)).thenReturn(Optional.empty());

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> precoProdutoTabelaService.cadastrar(precoProdutoTabela, 10, 10),
                    "Deveria lançar exceção se a tabelaPreco não existir"
            );

            assertEquals("Tabela preço não encontrada", exception.getMessage());
        }

        @Test
        @DisplayName("Deve lançar exceção se o produto não existir")
        void deveLancarExcecaoSeProdutoNaoExistir(){

            TabelaPreco tabelaPreco = new TabelaPreco();
            PrecoProdutoTabela precoProdutoTabela = new PrecoProdutoTabela();
            Mockito.when(tabelaPrecoRepository.findById(10)).thenReturn(Optional.of(tabelaPreco));
            Mockito.when(produtoRepository.findById(100)).thenReturn(Optional.empty());

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> precoProdutoTabelaService.cadastrar(precoProdutoTabela, 10, 100),
                    "Deveria lançar exceção se o produto não existir"
            );
            assertEquals("Produto não encontrado", exception.getMessage());
        }

        @Test
        @DisplayName("Deve lançar exceção se precoProduto for nulo e não for positivo")
        void deveLancarExcecaoSePrecoProdutoForZeroOuMenor(){

            PrecoProdutoTabela precoProdutoTabela = new PrecoProdutoTabela();

            when(produtoRepository.findById(1)).thenReturn(Optional.of(new Produto()));
            when(tabelaPrecoRepository.findById(1)).thenReturn(Optional.of(new TabelaPreco()));

            precoProdutoTabela.setPrecoProduto(null);
            assertThrows(EntidadeNulaException.class, () -> precoProdutoTabelaService.cadastrar(precoProdutoTabela, 1, 1));

            precoProdutoTabela.setPrecoProduto(0.0);
            assertThrows(EntidadeValorAbaixoDeZeroException.class, () -> precoProdutoTabelaService.cadastrar(precoProdutoTabela,1 ,1));

            precoProdutoTabela.setPrecoProduto(-10.0);
            assertThrows(EntidadeValorAbaixoDeZeroException.class, () -> precoProdutoTabelaService.cadastrar(precoProdutoTabela,1 ,1));
        }
    }

    @Nested
    @DisplayName("Método Listar")
    class MetodoListar{

        @Test
        @DisplayName("Deve retornar lista com itens")
        void deveRetornarListaComItens(){

            List<PrecoProdutoTabela> listaMockada = List.of(new PrecoProdutoTabela());
            when(precoProdutoTabelaRepository.findAll()).thenReturn(listaMockada);

            List<PrecoProdutoTabela> resultado = precoProdutoTabelaService.listar();

            assertEquals(1, resultado.size());
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando não houver itens")
        void deveRetornarListaVazia(){

            when(precoProdutoTabelaRepository.findAll()).thenReturn(List.of());

            List<PrecoProdutoTabela> resultado = precoProdutoTabelaService.listar();

            assertTrue(resultado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Método BuscarPorId")
    class MetodoBuscarPorId{

        @Test
        @DisplayName("Deve retornar item quando ele existir")
        void deveRetornarItemQuandoExistir(){

            PrecoProdutoTabela precoProdutoTabela = new PrecoProdutoTabela();
            when(precoProdutoTabelaRepository.findById(1)).thenReturn(Optional.of(precoProdutoTabela));

            PrecoProdutoTabela resultado = precoProdutoTabelaService.buscarPorId(1);

            assertSame(precoProdutoTabela, resultado);
        }

        @Test
        @DisplayName("Deve lançar exceção se o precoProdutoTabela não existir")
        void deveLancarExcecaoSePrecoProdutoTabelaNaoExistir(){

            when(precoProdutoTabelaRepository.findById(1)).thenReturn(Optional.empty());

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> precoProdutoTabelaService.buscarPorId(1),
                    "Deveria lançar exceção se o PrecoProdutoTabela não existir"
            );

            assertEquals("Preço Produto tabela não encontrado", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Método DeletarPorId")
    class MetodoDeletarPorId{

        @Test
        @DisplayName("Deve deletar quando PrecoProdutoTabela existir")
        void deveDeletarCorretamente(){

            when(precoProdutoTabelaRepository.existsById(1)).thenReturn(true);

            precoProdutoTabelaService.deletarPorId(1);

            verify(precoProdutoTabelaRepository).deleteById(1);
        }

        @Test
        @DisplayName("Deve lançar exceção quando tenta deletar PrecoProdutoTabela que não existe")
        void deveLancarExcecaoQuandoPrecoProdutoTabelaNaoExistir(){

            when(precoProdutoTabelaRepository.existsById(1)).thenReturn(false);

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> precoProdutoTabelaService.deletarPorId(1),
                    "Deveria lançar exceção se o PrecoProdutoTabela não existir"
            );

            assertEquals("Preço Produto tabela não encontrado", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Método Atualizar")
    class MetodoAtualizar{

        @Test
        @DisplayName("Deve atualizar quando o PrecoProdutoTabela existir")
        void deveAtualizarCorretamenteQuandoPrecoProdutoTabelaExistir(){

            PrecoProdutoTabela precoProdutoTabela = new PrecoProdutoTabela();
            precoProdutoTabela.setIdPrecoProdutoTabela(1);

            when(precoProdutoTabelaRepository.existsById(1)).thenReturn(true);
            when(precoProdutoTabelaRepository.save(precoProdutoTabela)).thenReturn(precoProdutoTabela);

            PrecoProdutoTabela resultado = precoProdutoTabelaService.atualizar(precoProdutoTabela);

            assertSame(precoProdutoTabela, resultado);
        }

        @Test
        @DisplayName("Deve lançar exceção quando tenta atualizar PrecoProdutoTabela que não existe")
        void deveLancarExcecaoQuandoPrecoProdutoTabelaNaoExistir(){

            PrecoProdutoTabela precoProdutoTabela = new PrecoProdutoTabela();
            precoProdutoTabela.setIdPrecoProdutoTabela(1);

            when(precoProdutoTabelaRepository.existsById(1)).thenReturn(false);

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> precoProdutoTabelaService.atualizar(precoProdutoTabela),
                    "Deveria lançar exceção se o PrecoProdutoTabela não existir"
            );

            assertEquals("Preço Produto tabela não encontrado", exception.getMessage());
        }

    }
}