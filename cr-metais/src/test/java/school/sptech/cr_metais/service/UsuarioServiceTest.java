package school.sptech.cr_metais.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import school.sptech.cr_metais.config.GerenciadorTokenJwt;
import school.sptech.cr_metais.dto.Usuario.UsuarioListarDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioTokenDto;
import school.sptech.cr_metais.entity.Usuario;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.UsuarioMapper;
import school.sptech.cr_metais.repository.UsuarioRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UsuarioService service;


    @BeforeEach
    void setup() {
        repository = mock(UsuarioRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        gerenciadorTokenJwt = mock(GerenciadorTokenJwt.class);
        authenticationManager = mock(AuthenticationManager.class);
        authentication = mock(Authentication.class);

        service = new UsuarioService(repository);

        ReflectionTestUtils.setField(service, "passwordEncoder", passwordEncoder);
        ReflectionTestUtils.setField(service, "usuarioRepository", repository);
        ReflectionTestUtils.setField(service, "gerenciadorTokenJwt", gerenciadorTokenJwt);
        ReflectionTestUtils.setField(service, "authenticationManager", authenticationManager);
    }

    @Nested
    @DisplayName("Método: listar")
    class ListarTests {

        @Test
        @DisplayName("Deve listar todos os usuários")
        void deveListarUsuarios() {
            Usuario usuario1 = new Usuario();
            Usuario usuario2 = new Usuario();

            when(repository.findAll()).thenReturn(List.of(usuario1, usuario2));

            List<Usuario> usuarios = service.listar();

            assertEquals(2, usuarios.size());
            verify(repository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("Método: buscarPorId")
    class BuscarPorIdTests {

        @Test
        @DisplayName("Deve buscar usuário por ID com sucesso")
        void deveBuscarUsuarioPorIdComSucesso() {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(10);

            when(repository.findById(10)).thenReturn(Optional.of(usuario));

            Usuario encontrado = service.buscarPorId(10);

            assertNotNull(encontrado);
            assertEquals(10, encontrado.getIdUsuario());
        }

        @Test
        @DisplayName("Deve lançar exceção ao buscar usuário inexistente")
        void deveFalharAoBuscarUsuarioInexistente() {
            when(repository.findById(99)).thenReturn(Optional.empty());

            assertThrows(EntidadeNaoEncontradaException.class,
                    () -> service.buscarPorId(99));
        }
    }

    @Nested
    @DisplayName("Método: deletar")
    class DeletarTests {

        @Test
        @DisplayName("Deve deletar usuário existente")
        void deveDeletarUsuario() {
            when(repository.existsById(3)).thenReturn(true);

            service.deletar(3);

            verify(repository).deleteById(3);
        }

        @Test
        @DisplayName("Deve lançar exceção ao tentar deletar usuário inexistente")
        void deveFalharAoDeletarInexistente() {
            when(repository.existsById(5)).thenReturn(false);

            assertThrows(EntidadeNaoEncontradaException.class,
                    () -> service.deletar(5));
        }
    }

    @Nested
    @DisplayName("Método: atualizar")
    class AtualizarTests {

        @Test
        @DisplayName("Deve atualizar usuário existente")
        void deveAtualizarUsuario() {

            Usuario existente = new Usuario();
            existente.setIdUsuario(1);
            existente.setNome("Fabinho");

            Usuario novo = new Usuario();
            novo.setNome("Fábio Henrique Tavares");
            novo.setEmail("fabinhoTavares@email.com");
            novo.setSenha("1234");

            when(repository.findById(1)).thenReturn(Optional.of(existente));
            when(repository.save(existente)).thenReturn(existente);

            Usuario atualizado = service.atualizar(1, novo);

            assertEquals("Fábio Henrique Tavares", atualizado.getNome());
            assertEquals("1234", atualizado.getSenha());
        }

        @Test
        @DisplayName("Deve falhar ao atualizar usuário inexistente")
        void deveFalharAoAtualizarInexistente() {
            when(repository.findById(2)).thenReturn(Optional.empty());

            assertThrows(EntidadeNaoEncontradaException.class,
                    () -> service.atualizar(2, new Usuario()));
        }
    }

    @Nested
    @DisplayName("Método: criar")
    class CriarTests {

        @Test
        @DisplayName("Deve criar usuário com senha criptografada")
        void deveCriarComSenhaCriptografada() {
            Usuario novo = new Usuario();
            novo.setSenha("123");

            when(passwordEncoder.encode("123")).thenReturn("senha_cripto");

            service.criar(novo);

            assertEquals("senha_cripto", novo.getSenha());
            verify(repository, times(1)).save(novo);
        }
    }

    @Nested
    @DisplayName("Método: autenticar")
    class AutenticarTests {

        @Test
        @DisplayName("Deve falhar ao autenticar com email inexistente")
        void deveFalharAutenticacaoEmailInexistente() {

            Usuario login = new Usuario();
            login.setEmail("lucasLima@gmail.com");
            login.setSenha("123");

            UsernamePasswordAuthenticationToken tokenCredenciais =
                    new UsernamePasswordAuthenticationToken("lucasLima@gmail.com", "123");

            when(authenticationManager.authenticate(tokenCredenciais)).thenReturn(authentication);
            when(repository.findByEmail("lucasLima@gmail.com")).thenReturn(Optional.empty());

            assertThrows(ResponseStatusException.class,
                    () -> service.autenticar(login));
        }
    }

    @Nested
    @DisplayName("Método: listarTodos")
    class ListarTodosTests {

        @Test
        @DisplayName("Deve listar todos os usuários com DTO")
        void deveListarTodosComDto() {

            Usuario u1 = new Usuario();
            u1.setNome("Alisson Becker");
            u1.setEmail("alissonBecker@email.com");
            u1.setSenha("123");

            UsuarioListarDto dto1 = new UsuarioListarDto();
            dto1.setNome("Roberto Firmino");
            dto1.setCargo("");
            dto1.setEmail("robertoFirmino@gmail.com");

            when(repository.findAll()).thenReturn(List.of(u1));

            try (MockedStatic<UsuarioMapper> mockMapper = mockStatic(UsuarioMapper.class)) {
                mockMapper.when(() -> UsuarioMapper.of(u1)).thenReturn(dto1);

                List<UsuarioListarDto> dtos = service.listarTodos();

                assertEquals(1, dtos.size());
                assertEquals("Roberto Firmino", dtos.getFirst().getNome());
            }
        }
    }
}
