package school.sptech.cr_metais.mappers;

import school.sptech.cr_metais.dto.Usuario.UsuarioCriacaoDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioListarDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioLoginDto;
import school.sptech.cr_metais.dto.Usuario.UsuarioTokenDto;
import school.sptech.cr_metais.entity.Usuario;

public class UsuarioMapper {

    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto){
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setCargo(usuarioCriacaoDto.getCargo());
        return usuario;
    }

    public static Usuario of(UsuarioLoginDto usuarioLoginDto){

        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioLoginDto.getEmail());
        usuario.setSenha(usuarioLoginDto.getSenha());

        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getIdUsuario());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }

    public static UsuarioListarDto of(Usuario usuario){
        UsuarioListarDto usuarioListarDto = new UsuarioListarDto();

        usuarioListarDto.setIdUsuario(usuario.getIdUsuario());
        usuarioListarDto.setEmail(usuario.getEmail());
        usuarioListarDto.setNome(usuario.getNome());
        usuarioListarDto.setCargo(usuario.getCargo());
        return usuarioListarDto;
    }

}
