package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.entity.Endereco;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.EnderecoMapper;
import school.sptech.cr_metais.repository.EnderecoRepository;
import school.sptech.cr_metais.repository.UsuarioRepository;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoMapper mapper;

    public EnderecoService(EnderecoRepository repository, UsuarioRepository usuarioRepository, EnderecoMapper mapper) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    public Endereco cadastrar(Endereco enderecoParaCadastro) {

        Endereco enderecoRegistrado = repository.save(enderecoParaCadastro);

        return enderecoRegistrado;

    }

    public List<Endereco> listar(){
        return repository.findAll();
    }

    public Endereco buscarPorId(Integer id){
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado"));
    }

    public void deletarPorId(Integer id){
        Boolean existe = repository.existsById(id);

        if(!existe){
            throw new EntidadeNaoEncontradaException("Endereço não encontrado");
        }

        repository.deleteById(id);
    }

    public Endereco atualizar(Endereco enderecoParaAtualizar){
        if(!repository.existsById(enderecoParaAtualizar.getIdEndereco())){
            throw new EntidadeNaoEncontradaException("Endereço não encontrado");
        }

        return repository.save(enderecoParaAtualizar);
    }

}

