package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.EnderecoCadastroDto.EnderecoCadastroDto;
import school.sptech.cr_metais.dto.EnderecoCadastroDto.EnderecoGetDto;
import school.sptech.cr_metais.entity.Endereco;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.EnderecoMapper;
import school.sptech.cr_metais.repository.EnderecoRepository;

import java.util.Optional;

@Service
public class EnderecoService {
    private final EnderecoRepository repository;
    private final EnderecoMapper enderecoMapper;

    public EnderecoService(EnderecoRepository repository, EnderecoMapper enderecoMapper) {
        this.repository = repository;
        this.enderecoMapper = enderecoMapper;
    }

    public Endereco cadastrar(EnderecoCadastroDto dto) {
        Endereco endereco = enderecoMapper.toEntity(dto);
        return repository.save(endereco);
    }

    public EnderecoGetDto buscarPorId(Integer id) {
        Optional<Endereco> endereco = repository.findById(id);
        if (endereco.isPresent()) {
            EnderecoGetDto enderecoRetorno = enderecoMapper.toDtoGet(endereco.get());
            return enderecoRetorno;
        }
        return null;

    }

    public void deletarPorId(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Endereço não encontrado");
        }
        repository.deleteById(id);
    }

    public Endereco atualizar(Integer id, Endereco endereco) {
        Optional<Endereco> enderecoDb = repository.findById(id);
        if (!enderecoDb.isPresent()) {
            throw new EntidadeNaoEncontradaException("Endereço não encontrado");
        }else{
            enderecoDb.get().setLogradouro(endereco.getLogradouro());
            enderecoDb.get().setNumero(endereco.getNumero());
            enderecoDb.get().setComplemento(endereco.getComplemento());
            enderecoDb.get().setBairro(endereco.getBairro());
            enderecoDb.get().setCep(endereco.getCep());
            enderecoDb.get().setCidade(endereco.getCidade());
            enderecoDb.get().setEstado(endereco.getEstado());

            return repository.save(enderecoDb.get());
        }
    }
}
