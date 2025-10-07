package school.sptech.cr_metais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.entity.Usuario;
import school.sptech.cr_metais.exception.EntidadeConflitoException;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository uRepository;

    public UsuarioService(UsuarioRepository uRepository) {
        this.uRepository = uRepository;
    }

    public Usuario cadastrar(Usuario usuarioCadastro){
        if (uRepository.existsByEmail(usuarioCadastro.getEmail())){
            throw new EntidadeConflitoException("E-mail já cadastrado");
        }
        return uRepository.save(usuarioCadastro);
    }

    public List<Usuario> listar(){
        return uRepository.findAll();
    }

    public Usuario buscarPorId(Integer id){
        return uRepository.findById(id)
                .orElseThrow(
                        ()-> new
                                EntidadeNaoEncontradaException
                                ("Usuário não encontrado")
                );
    }

    public void deletar(Integer id){
        if (!uRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Usuário não encontrado");
        }
        uRepository.deleteById(id);
    }

    public Usuario atualizar(Integer id, Usuario usuarioAtualizado){
        Usuario usuarioExistente = uRepository.findById(id)
                .orElseThrow(
                        ()-> new EntidadeNaoEncontradaException("Usuário não encontrado")
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


}
