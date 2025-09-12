package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.exception.EntidadeConflitoException;
import school.sptech.cr_metais.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository pRepository;

    public ProdutoService(ProdutoRepository pRepository) {
        this.pRepository = pRepository;
    }

    public Produto cadastrar(Produto produtoParaCadastro){

        if (pRepository.existsByNome(produtoParaCadastro.getNome())){
            throw new EntidadeConflitoException("Conflito de IDs");
        }

        return pRepository.save(produtoParaCadastro);
    }

    public List<Produto> listar(){
        return pRepository.findAll();
    }


}
