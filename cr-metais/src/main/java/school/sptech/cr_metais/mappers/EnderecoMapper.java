package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.EnderecoCadastroDto.EnderecoGetDto;
import school.sptech.cr_metais.dto.EnderecoCadastroDto.EnderecoRequestDto;
import school.sptech.cr_metais.dto.EnderecoCadastroDto.EnderecoResponseDto;
import school.sptech.cr_metais.entity.Endereco;
import school.sptech.cr_metais.entity.Usuario;

@Component
public class EnderecoMapper {

    public static Endereco toEntity(EnderecoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Endereco endereco = new Endereco();
        endereco.setEstado(dto.getEstado());
        endereco.setCidade(dto.getCidade());
        endereco.setCep(dto.getCep());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setNumero(dto.getNumero());

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        endereco.setUsuario(usuario);

        return endereco;
    }

    public static EnderecoResponseDto toResponse(Endereco endereco) {
        if (endereco == null) {
            return null;
        }

        EnderecoResponseDto dto = new EnderecoResponseDto();
        dto.setId(endereco.getId());
        dto.setEstado(endereco.getEstado());
        dto.setCidade(endereco.getCidade());
        dto.setCep(endereco.getCep());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setComplemento(endereco.getComplemento());
        dto.setBairro(endereco.getBairro());
        dto.setNumero(endereco.getNumero());

        dto.setUsuarioId(
                endereco.getUsuario() != null ? endereco.getUsuario().getId() : null
        );

        return dto;
    }
}