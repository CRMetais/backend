package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.EnderecoCadastroDto.EnderecoCadastroDto;
import school.sptech.cr_metais.dto.EnderecoCadastroDto.EnderecoGetDto;
import school.sptech.cr_metais.entity.Endereco;

@Component
public class EnderecoMapper {
    public static Endereco toEntity(EnderecoCadastroDto enderecoCadastro) {
        if (enderecoCadastro == null) {
            return null;
        }

        Endereco endereco = new Endereco();

        endereco.setCep(enderecoCadastro.getCep());
        endereco.setComplemento(enderecoCadastro.getComplemento());
        endereco.setEstado(enderecoCadastro.getEstado());
        endereco.setBairro(enderecoCadastro.getBairro());
        endereco.setCidade(enderecoCadastro.getCidade());
        endereco.setComplemento(enderecoCadastro.getComplemento());
        endereco.setLogradouro(enderecoCadastro.getLogradouro());
        endereco.setNumero(enderecoCadastro.getNumero());

        return endereco;
    }

    public static EnderecoGetDto toDtoGet(Endereco endereco) {

        EnderecoGetDto enderecoGetDto = new EnderecoGetDto();
        enderecoGetDto.setBairro(endereco.getBairro());
        enderecoGetDto.setCidade(endereco.getCidade());
        enderecoGetDto.setComplemento(endereco.getComplemento());
        enderecoGetDto.setEstado(endereco.getEstado());
        enderecoGetDto.setCep(endereco.getCep());
        enderecoGetDto.setNumero(endereco.getNumero());
        enderecoGetDto.setLogradouro(endereco.getLogradouro());

        return enderecoGetDto;
    }
}
