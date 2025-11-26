package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Produto.ProdutoResponseDto;
import school.sptech.cr_metais.entity.Estoque;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.ProdutoMapper;
import school.sptech.cr_metais.repository.EstoqueRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository pRepository;
    private final EstoqueRepository eRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository pRepository, EstoqueRepository eRepository, ProdutoMapper produtoMapper) {
        this.pRepository = pRepository;
        this.eRepository = eRepository;
        this.produtoMapper = produtoMapper;
    }

    public Produto cadastrar(Produto produtoParaCadastrar, Integer idEstoque) {

        Optional <Estoque> estoqueOpt = eRepository.findById(idEstoque);

        if (estoqueOpt.isEmpty()){
            throw new EntidadeNaoEncontradaException("Estoque não encontrado");
        }

        Estoque estoque = estoqueOpt.get();

        produtoParaCadastrar.setEstoque(estoque);

        Produto produtoRegistrado = pRepository.save(produtoParaCadastrar);
        return produtoRegistrado;

    }

    public List<Produto> listar() {
        return pRepository.findAll();
    }

    public Produto buscarPorId(Integer id) {
        return pRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));

    }

    public void deletar(Integer id) {
        if (!pRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Produto não encontrado");
        }
        pRepository.deleteById(id);
    }

    public Produto atualizar(Produto produto) {

        if (!pRepository.existsById(produto.getIdProduto())){
            throw new EntidadeNaoEncontradaException("Produto não encontrado");
        }

        return pRepository.save(produto);
    }

}
