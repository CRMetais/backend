package school.sptech.cr_metais.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.cr_metais.config.GerenciadorTokenJwt;
import school.sptech.cr_metais.dto.Usuario.UsuarioAtualizacaoDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioListarDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioTokenDto;
import school.sptech.cr_metais.entity.Usuario;
import school.sptech.cr_metais.exception.EntidadeConflitoException;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.UsuarioMapper;
import school.sptech.cr_metais.repository.FornecedorRepository;
import school.sptech.cr_metais.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    // Todas as dependências centralizadas e imutáveis (final)
    private final UsuarioRepository usuarioRepository;
    private final FornecedorRepository fRepository;
    private final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    // Um único construtor para gerenciar todas as injeções de dependência sem usar @Autowired solto
    public UsuarioService(UsuarioRepository usuarioRepository,
                          FornecedorRepository fRepository,
                          PasswordEncoder passwordEncoder,
                          GerenciadorTokenJwt gerenciadorTokenJwt,
                          AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.fRepository = fRepository;
        this.passwordEncoder = passwordEncoder;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.authenticationManager = authenticationManager;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public List<UsuarioListarDto> listarTodos() {
        List<Usuario> usuariosEncontrados = usuarioRepository.findAll();
        return usuariosEncontrados.stream().map(UsuarioMapper::of).toList();
    }

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
    }

    public void criar(Usuario novoUsuario) {
        // Validação: Impede cadastro de e-mail duplicado
        if (usuarioRepository.existsByEmail(novoUsuario.getEmail())) {
            throw new EntidadeConflitoException("O e-mail informado já está cadastrado no sistema.");
        }

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        this.usuarioRepository.save(novoUsuario);
    }

    public Usuario atualizar(Integer id, UsuarioAtualizacaoDto dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        // Validação: Se alterou o e-mail, verifica se o novo e-mail já pertence a OUTRO usuário
        if (!usuarioExistente.getEmail().equalsIgnoreCase(dto.getEmail())) {
            Optional<Usuario> usuarioComMesmoEmail = usuarioRepository.findByEmail(dto.getEmail());
            if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getIdUsuario().equals(id)) {
                throw new EntidadeConflitoException("Não é possível atualizar. O e-mail informado já pertence a outro usuário.");
            }
        }

        usuarioExistente.setNome(dto.getNome());
        usuarioExistente.setEmail(dto.getEmail());
        usuarioExistente.setCargo(dto.getCargo());

        return usuarioRepository.save(usuarioExistente);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Usuário não encontrado");
        }

        // Pega o e-mail do usuário autenticado via token JWT
        String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario usuarioAutenticado = usuarioRepository.findByEmail(emailAutenticado)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário autenticado não encontrado"));

        if (usuarioAutenticado.getIdUsuario().equals(id)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não pode excluir sua própria conta"
            );
        }

        fRepository.desassociarResponsavel(id);
        usuarioRepository.deleteById(id);
    }

    public UsuarioTokenDto autenticar(Usuario usuario) {
        final UsernamePasswordAuthenticationToken credentials =
                new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado = usuarioRepository.findByEmail(usuario.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email do usuário não cadastrado"));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }
}