package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.entity.Estoque;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.EstoqueMapper;
import school.sptech.cr_metais.repository.EstoqueRepository;

import java.util.List;

@Service
public class EstoqueService {

    private final EstoqueRepository repository;
    private final EstoqueMapper mapper;

    public EstoqueService(EstoqueRepository repository, EstoqueMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Estoque cadastrar(Estoque estoqueParaCadastrar){

        Estoque estoqueCadastrado = repository.save(estoqueParaCadastrar);
        return estoqueCadastrado;

    }

    public List<Estoque> listar(){
        return repository.findAll();
    }

    public Estoque buscarPorId(Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Estoque não encontrado"));
    }

    public void deletarPorId(Integer id){
        Boolean existe = repository.existsById(id);

        if (!existe){
            throw new EntidadeNaoEncontradaException("Estoque não encontrado");
        }

        repository.deleteById(id);

    }

    public Estoque atualizar(Estoque estoque){

        if (!repository.existsById(estoque.getIdEstoque())){
            throw new EntidadeNaoEncontradaException("Estoque não encontrado");
        }

        return repository.save(estoque);
    }
}
