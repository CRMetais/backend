package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.entity.Estoque;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.EstoqueMapper;
import school.sptech.cr_metais.repository.EstoqueRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    private final EstoqueRepository repository;
    private final EstoqueMapper mapper;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository repository, EstoqueMapper mapper, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.produtoRepository = produtoRepository;
    }

    public Estoque cadastrar(Estoque estoqueParaCadastrar){
        
        if (estoqueParaCadastrar.getProduto() != null && estoqueParaCadastrar.getProduto().getIdProduto() != null) {
            Optional<Produto> produtoOpt = produtoRepository.findById(estoqueParaCadastrar.getProduto().getIdProduto());
            if (produtoOpt.isEmpty()) {
                throw new EntidadeNaoEncontradaException("Produto não encontrado");
            }
            estoqueParaCadastrar.setProduto(produtoOpt.get());
        }
        
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
