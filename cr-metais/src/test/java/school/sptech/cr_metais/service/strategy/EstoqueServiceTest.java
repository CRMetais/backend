package school.sptech.cr_metais.service.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import school.sptech.cr_metais.entity.Estoque;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.EstoqueMapper;
import school.sptech.cr_metais.repository.EstoqueRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstoqueServiceTest {

    @Mock
    private EstoqueRepository repository;

    @Mock
    private EstoqueMapper mapper;

    @InjectMocks
    private EstoqueService service;

    private Estoque estoque;

    @BeforeEach
    void setup() {
        estoque = new Estoque();
        estoque.setIdEstoque(1);
        estoque.setQuantidadeDisponivel(10);
    }

    @Nested
    @DisplayName("Método cadastrar()")
    class CadastrarTests {

        @Test
        @DisplayName("Deve cadastrar um estoque com sucesso")
        void deveCadastrarComSucesso() {
            when(repository.save(estoque)).thenReturn(estoque);

            Estoque resultado = service.cadastrar(estoque);

            assertNotNull(resultado);
            assertEquals(estoque, resultado);
            verify(repository).save(estoque);
        }
    }

    @Nested
    @DisplayName("Método listar()")
    class ListarTests {

        @Test
        @DisplayName("Deve listar todos os estoques com sucesso")
        void deveListarComSucesso() {
            List<Estoque> lista = List.of(estoque);

            when(repository.findAll()).thenReturn(lista);

            List<Estoque> resultado = service.listar();

            assertNotNull(resultado);
            assertEquals(1, resultado.size());
            verify(repository).findAll();
        }
    }

    @Nested
    @DisplayName("Método buscarPorId()")
    class BuscarPorIdTests {

        @Test
        @DisplayName("Deve retornar estoque quando o ID existir")
        void deveBuscarPorIdComSucesso() {
            when(repository.findById(1)).thenReturn(Optional.of(estoque));

            Estoque resultado = service.buscarPorId(1);

            assertNotNull(resultado);
            assertEquals(estoque, resultado);
            verify(repository).findById(1);
        }

        @Test
        @DisplayName("Deve lançar exceção quando o ID não existir")
        void deveLancarExcecaoQuandoIdNaoExistir() {
            when(repository.findById(1)).thenReturn(Optional.empty());

            assertThrows(EntidadeNaoEncontradaException.class, () -> service.buscarPorId(1));

            verify(repository).findById(1);
        }
    }

    @Nested
    @DisplayName("Método deletarPorId()")
    class DeletarPorIdTests {

        @Test
        @DisplayName("Deve deletar estoque quando ID existir")
        void deveDeletarComSucesso() {
            when(repository.existsById(1)).thenReturn(true);

            assertDoesNotThrow(() -> service.deletarPorId(1));

            verify(repository).existsById(1);
            verify(repository).deleteById(1);
        }

        @Test
        @DisplayName("Deve lançar exceção ao deletar quando ID não existir")
        void deveLancarExcecaoAoDeletarIdInexistente() {
            when(repository.existsById(1)).thenReturn(false);

            assertThrows(EntidadeNaoEncontradaException.class, () -> service.deletarPorId(1));

            verify(repository).existsById(1);
            verify(repository, never()).deleteById(any());
        }
    }

    @Nested
    @DisplayName("Método atualizar()")
    class AtualizarTests {

        @Test
        @DisplayName("Deve atualizar estoque quando ID existir")
        void deveAtualizarComSucesso() {
            when(repository.existsById(1)).thenReturn(true);
            when(repository.save(estoque)).thenReturn(estoque);

            Estoque resultado = service.atualizar(estoque);

            assertNotNull(resultado);
            assertEquals(estoque, resultado);

            verify(repository).existsById(1);
            verify(repository).save(estoque);
        }

        @Test
        @DisplayName("Deve lançar exceção ao atualizar estoque inexistente")
        void deveLancarExcecaoAoAtualizarInexistente() {
            when(repository.existsById(1)).thenReturn(false);

            assertThrows(EntidadeNaoEncontradaException.class, () -> service.atualizar(estoque));

            verify(repository).existsById(1);
            verify(repository, never()).save(any());
        }
    }
}
