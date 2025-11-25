package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.entity.PrecoProdutoTabela;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.PrecoProdutoTabelaMapper;
import school.sptech.cr_metais.repository.PrecoProdutoTabelaRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PrecoProdutoTabelaService {

    private final PrecoProdutoTabelaRepository precoProdutoTabelaRepository;
    private final TabelaPrecoRepository tabelaPrecoRepository;
    private final ProdutoRepository produtoRepository;
    private final PrecoProdutoTabelaMapper mapper;

    public PrecoProdutoTabelaService(
            PrecoProdutoTabelaRepository precoProdutoTabelaRepository,
            TabelaPrecoRepository tabelaPrecoRepository,
            ProdutoRepository produtoRepository,
            PrecoProdutoTabelaMapper mapper
    ) {
        this.precoProdutoTabelaRepository = precoProdutoTabelaRepository;
        this.tabelaPrecoRepository = tabelaPrecoRepository;
        this.produtoRepository = produtoRepository;
        this.mapper = mapper;
    }

    public PrecoProdutoTabela cadastrar(PrecoProdutoTabela precoProdutoTabelaPraCadastro, Integer idTabalaPreco, Integer idProduto){
        Optional<TabelaPreco> tabelaPrecoOpt = tabelaPrecoRepository.findById(idTabalaPreco);
        Optional<Produto> produtoOpt = produtoRepository.findById(idProduto);

        if (tabelaPrecoOpt.isEmpty()){
            throw new EntidadeNaoEncontradaException("Tabela preço não encontrada");
        }

        if (produtoOpt.isEmpty()){
            throw new EntidadeNaoEncontradaException("Produto não encontrado");
        }

        TabelaPreco tabelaPreco = tabelaPrecoOpt.get();
        Produto produto = produtoOpt.get();

        precoProdutoTabelaPraCadastro.setTabelaPreco(tabelaPreco);
        precoProdutoTabelaPraCadastro.setProduto(produto);

        PrecoProdutoTabela precoProdutoTabelaRegistrado = precoProdutoTabelaRepository.save(precoProdutoTabelaPraCadastro);
        return precoProdutoTabelaRegistrado;
    }

    public List<PrecoProdutoTabela> listar(){
        return precoProdutoTabelaRepository.findAll();
    }

    public PrecoProdutoTabela buscarPorId(Integer id){
        return precoProdutoTabelaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Preço Produto tabela não encontrado"));
    }

    public void deletarPorId(Integer id){
        Boolean existe = precoProdutoTabelaRepository.existsById(id);

        if (!existe){
            throw new EntidadeNaoEncontradaException("Preço Produto tabela não encontrado");
        }
        precoProdutoTabelaRepository.deleteById(id);
    }

    public PrecoProdutoTabela atualizar(PrecoProdutoTabela precoProdutoTabela){
        if (!precoProdutoTabelaRepository.existsById(precoProdutoTabela.getIdPrecoProdutoTabela())){
            throw new EntidadeNaoEncontradaException("Preço Produto tabela não encontrado");
        }

        return precoProdutoTabelaRepository.save(precoProdutoTabela);
    }

}
