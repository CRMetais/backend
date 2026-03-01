package school.sptech.cr_metais.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.cr_metais.entity.Usuario;
import school.sptech.cr_metais.repository.UsuarioRepository;
import school.sptech.cr_metais.service.AutenticacaoService;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutenticacaoProviderTest {

    @Mock
    private AutenticacaoService autenticacaoService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private AutenticacaoProvider autenticacaoProvider;

    @Nested
    @DisplayName("Método: authenticate")
    class AuthenticateTests {

        @Test
        @DisplayName("Deve autenticar usuário com senha BCrypt")
        void deveAutenticarComSenhaBcrypt() {
            String email = "usuario@empresa.com";
            String senhaDigitada = "123456";
            String senhaHash = "$2a$10$aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

            when(autenticacaoService.loadUserByUsername(email)).thenReturn(userDetails);
            when(userDetails.getPassword()).thenReturn(senhaHash);
            when(userDetails.getAuthorities()).thenReturn(Collections.emptyList());
            when(passwordEncoder.matches(senhaDigitada, senhaHash)).thenReturn(true);

            Authentication authentication = autenticacaoProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(email, senhaDigitada)
            );

            assertNotNull(authentication);
            assertTrue(authentication.isAuthenticated());
            verify(passwordEncoder, times(1)).matches(senhaDigitada, senhaHash);
            verify(usuarioRepository, never()).save(any(Usuario.class));
        }

        @Test
        @DisplayName("Deve migrar senha legada em texto puro para BCrypt no login válido")
        void deveMigrarSenhaLegada() {
            String email = "usuario@empresa.com";
            String senhaLegada = "123456";
            String senhaHashNova = "$2a$10$bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";

            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senhaLegada);

            when(autenticacaoService.loadUserByUsername(email)).thenReturn(userDetails);
            when(userDetails.getPassword()).thenReturn(senhaLegada);
            when(userDetails.getAuthorities()).thenReturn(Collections.emptyList());
            when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));
            when(passwordEncoder.encode(senhaLegada)).thenReturn(senhaHashNova);

            Authentication authentication = autenticacaoProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(email, senhaLegada)
            );

            assertNotNull(authentication);
            assertEquals(senhaHashNova, usuario.getSenha());
            verify(usuarioRepository, times(1)).save(usuario);
            verify(passwordEncoder, never()).matches(anyString(), anyString());
        }

        @Test
        @DisplayName("Deve rejeitar credenciais inválidas")
        void deveRejeitarCredenciaisInvalidas() {
            String email = "usuario@empresa.com";
            String senhaDigitada = "errada";
            String senhaHash = "$2a$10$ccccccccccccccccccccccccccccccccccccccccccccccccccccc";

            when(autenticacaoService.loadUserByUsername(email)).thenReturn(userDetails);
            when(userDetails.getPassword()).thenReturn(senhaHash);
            when(passwordEncoder.matches(senhaDigitada, senhaHash)).thenReturn(false);

            assertThrows(BadCredentialsException.class,
                    () -> autenticacaoProvider.authenticate(new UsernamePasswordAuthenticationToken(email, senhaDigitada)));
        }
    }
}
