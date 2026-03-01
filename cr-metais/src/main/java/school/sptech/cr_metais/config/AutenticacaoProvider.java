package school.sptech.cr_metais.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.cr_metais.entity.Usuario;
import school.sptech.cr_metais.repository.UsuarioRepository;
import school.sptech.cr_metais.service.AutenticacaoService;

import java.util.Optional;
import java.util.regex.Pattern;

public class AutenticacaoProvider implements AuthenticationProvider {

    private static final Pattern BCRYPT_PATTERN = Pattern.compile("^\\$2[aby]\\$\\d{2}\\$[./A-Za-z0-9]{53}$");

    private final AutenticacaoService usuarioAutorizacaoService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public AutenticacaoProvider(
            AutenticacaoService usuarioAutorizacaoService,
            PasswordEncoder passwordEncoder,
            UsuarioRepository usuarioRepository
    ) {
        this.usuarioAutorizacaoService = usuarioAutorizacaoService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException{
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        UserDetails userDetails;
        try {
            userDetails = this.usuarioAutorizacaoService.loadUserByUsername(username);
        } catch (Exception ex) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        String senhaSalva = userDetails.getPassword();

        if (isBcryptHash(senhaSalva)) {
            if (this.passwordEncoder.matches(password, senhaSalva)) {
                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            }
            throw new BadCredentialsException("Credenciais inválidas");
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(username);
        if (usuarioOpt.isPresent() && senhaSalva != null && senhaSalva.equals(password)) {
            Usuario usuario = usuarioOpt.get();
            usuario.setSenha(passwordEncoder.encode(password));
            usuarioRepository.save(usuario);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }

        throw new BadCredentialsException("Credenciais inválidas");
    }

    private boolean isBcryptHash(String passwordHash) {
        return passwordHash != null && BCRYPT_PATTERN.matcher(passwordHash).matches();
    }

    @Override
    public boolean supports(final Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
