package school.sptech.cr_metais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Usuario.UsuarioDetalhesDto;
import school.sptech.cr_metais.entity.Usuario;
import school.sptech.cr_metais.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Credenciais inválidas"));
        return new UsuarioDetalhesDto(usuario);
    }
}
