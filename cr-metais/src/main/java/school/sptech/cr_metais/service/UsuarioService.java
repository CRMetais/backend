package school.sptech.cr_metais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.cr_metais.config.GerenciadorTokenJwt;
import school.sptech.cr_metais.dto.Usuario.UsuarioListarDto;
import school.sptech.cr_metais.mappers.UsuarioMapper;
import school.sptech.cr_metais.dto.Usuario.UsuarioTokenDto;
import school.sptech.cr_metais.entity.Usuario;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository uRepository;

    public UsuarioService(UsuarioRepository uRepository) {
        this.uRepository = uRepository;
    }

//    public Usuario cadastrar(Usuario usuarioCadastro){
//        if (uRepository.findByEmail(usuarioCadastro.getEmail())){
//            throw new EntidadeConflitoException("E-mail já cadastrado");
//        }
//        return uRepository.save(usuarioCadastro);
//    }

    public List<Usuario> listar() {
        return uRepository.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        return uRepository.findById(id)
                .orElseThrow(
                        () -> new
                                EntidadeNaoEncontradaException
                                ("Usuário não encontrado")
                );
    }

    public void deletar(Integer id) {
        if (!uRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Usuário não encontrado");
        }
        uRepository.deleteById(id);
    }

    public Usuario atualizar(Integer id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = uRepository.findById(id)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException("Usuário não encontrado")
                );
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());

        return uRepository.save(usuarioExistente);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void criar(Usuario novoUsuario) {

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());

        novoUsuario.setSenha(senhaCriptografada);

        this.usuarioRepository.save(novoUsuario);


    }

    public UsuarioTokenDto autenticar(Usuario usuario) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario uauarioAutenticado = usuarioRepository.findByEmail(usuario.getEmail()).orElseThrow(
                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return UsuarioMapper.of(uauarioAutenticado, token);


    }

    public List<UsuarioListarDto> listarTodos() {

        List<Usuario> usuariosEncontrados = usuarioRepository.findAll();

        return usuariosEncontrados.stream().map(UsuarioMapper::of).toList();
    }


}
