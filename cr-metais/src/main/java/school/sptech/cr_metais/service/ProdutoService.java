package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.exception.EntidadeConflitoException;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.repository.ProdutoRepository;

import java.util.List;

@Service
public class    ProdutoService {

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

    public List<Produto> listarPorPrecoMaior(){
        List<Produto> all = pRepository.findAll();
        int size = all.size() - 1;
        int i = 0;



        while(i < size){
            int j = 0;
            while(j < size - i){
                Produto atual = all.get(j);
                Produto proximo = all.get(j+1);
                if (atual.getPrecoKg() < proximo.getPrecoKg()){
                    Produto aux = all.get(j);
                    all.set(j, all.get(j+1));
                    all.set(j + 1,aux);
                }
                j++;
            } i++;


        }
        return all;
    }


    public List<Produto> listar(){
        return pRepository.findAll();
    }

    public void deletar(Integer id){
        if (!pRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Produto não encontrado");
        }
        pRepository.deleteById(id);
    }

    public Produto buscarPorId(Integer id){
            return pRepository.findById(id)
                    .orElseThrow(
                            ()-> new
                                    EntidadeNaoEncontradaException
                                    ("Produto não encontrado")
                    );
    }

    public Produto atualizar(Integer id, Produto produtoAtualizado){
        Produto produtoExistente = pRepository.findById(id)
                .orElseThrow(
                        ()-> new EntidadeNaoEncontradaException("Produto não encontrado")
                );
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setTipoProduto(produtoAtualizado.getTipoProduto());
        produtoExistente.setPrecoKg(produtoAtualizado.getPrecoKg());

        return pRepository.save(produtoExistente);
    }
}
