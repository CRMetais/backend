package school.sptech.cr_metais.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.cr_metais.entity.Endereco;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.EnderecoMapper;
import school.sptech.cr_metais.repository.EnderecoRepository;
import school.sptech.cr_metais.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnderecoServiceTest {

    private EnderecoRepository repository;
    private UsuarioRepository usuarioRepository;
    private EnderecoMapper mapper;
    private EnderecoService service;

    @BeforeEach
    void setup() {
        repository = mock(EnderecoRepository.class);
        usuarioRepository = mock(UsuarioRepository.class);
        mapper = mock(EnderecoMapper.class);

        service = new EnderecoService(repository, usuarioRepository, mapper);
    }

    @Nested
    @DisplayName("Método: cadastrar")
    class CadastrarTests {

        @Test
        @DisplayName("Deve cadastrar um endereço com sucesso")
        void deveCadastrarEnderecoComSucesso() {
            Endereco endereco = new Endereco();
            endereco.setIdEndereco(1);

            when(repository.save(endereco)).thenReturn(endereco);

            Endereco resultado = service.cadastrar(endereco);

            assertNotNull(resultado);
            assertEquals(1, resultado.getIdEndereco());
            verify(repository, times(1)).save(endereco);
        }
    }

    @Nested
    @DisplayName("Método: listar")
    class ListarTests {

        @Test
        @DisplayName("Deve listar todos os endereços")
        void deveListarEnderecos() {
            Endereco endereco1 = new Endereco();
            Endereco endereco2 = new Endereco();

            when(repository.findAll()).thenReturn(List.of(endereco1, endereco2));

            List<Endereco> lista = service.listar();

            assertEquals(2, lista.size());
            verify(repository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("Método: buscarPorId")
    class BuscarPorIdTests {

        @Test
        @DisplayName("Deve buscar endereço pelo ID com sucesso")
        void deveBuscarEnderecoPorIdComSucesso() {
            Endereco endereco = new Endereco();
            endereco.setIdEndereco(10);

            when(repository.findById(10)).thenReturn(Optional.of(endereco));

            Endereco encontrado = service.buscarPorId(10);

            assertNotNull(encontrado);
            assertEquals(10, encontrado.getIdEndereco());
            verify(repository, times(1)).findById(10);
        }

        @Test
        @DisplayName("Deve lançar exceção ao buscar endereço inexistente")
        void deveFalharAoBuscarEnderecoPorIdInexistente() {
            when(repository.findById(99)).thenReturn(Optional.empty());

            assertThrows(EntidadeNaoEncontradaException.class,
                    () -> service.buscarPorId(99));

            verify(repository, times(1)).findById(99);
        }
    }

    @Nested
    @DisplayName("Método: deletarPorId")
    class DeletarTests {

        @Test
        @DisplayName("Deve deletar endereço quando o ID existir")
        void deveDeletarEnderecoComSucesso() {
            when(repository.existsById(5)).thenReturn(true);

            service.deletarPorId(5);

            verify(repository, times(1)).existsById(5);
            verify(repository, times(1)).deleteById(5);
        }

        @Test
        @DisplayName("Deve lançar exceção ao tentar deletar endereço inexistente")
        void deveFalharAoDeletarEnderecoInexistente() {
            when(repository.existsById(88)).thenReturn(false);

            assertThrows(EntidadeNaoEncontradaException.class,
                    () -> service.deletarPorId(88));

            verify(repository, times(1)).existsById(88);
            verify(repository, times(0)).deleteById(any());
        }
    }

    @Nested
    @DisplayName("Método: atualizar")
    class AtualizarTests {

        @Test
        @DisplayName("Deve atualizar endereço existente com sucesso")
        void deveAtualizarEnderecoComSucesso() {
            Endereco endereco = new Endereco();
            endereco.setIdEndereco(7);

            when(repository.existsById(7)).thenReturn(true);
            when(repository.save(endereco)).thenReturn(endereco);

            Endereco atualizado = service.atualizar(endereco);

            assertNotNull(atualizado);
            assertEquals(7, atualizado.getIdEndereco());
            verify(repository, times(1)).existsById(7);
            verify(repository, times(1)).save(endereco);
        }

        @Test
        @DisplayName("Deve lançar exceção ao tentar atualizar endereço inexistente")
        void deveFalharAoAtualizarEnderecoInexistente() {
            Endereco endereco = new Endereco();
            endereco.setIdEndereco(123);

            when(repository.existsById(123)).thenReturn(false);

            assertThrows(EntidadeNaoEncontradaException.class,
                    () -> service.atualizar(endereco));

            verify(repository, times(1)).existsById(123);
            verify(repository, times(0)).save(any());
        }
    }
}
