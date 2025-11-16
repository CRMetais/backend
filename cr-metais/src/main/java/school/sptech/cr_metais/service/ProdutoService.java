package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Produto.ProdutoCadastrarDto;
import school.sptech.cr_metais.dto.Produto.ProdutoDetalhesDto;
import school.sptech.cr_metais.dto.Produto.ProdutoListarDto;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.exception.EntidadeConflitoException;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.ProdutoMapper;
import school.sptech.cr_metais.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository pRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository pRepository, ProdutoMapper produtoMapper) {
        this.pRepository = pRepository;
        this.produtoMapper = produtoMapper;
    }

    public ProdutoListarDto cadastrar(ProdutoCadastrarDto dto) {

        if (pRepository.existsByNome(dto.getNome())) {
            throw new EntidadeConflitoException("Produto já existe");
        }

        Produto produto = produtoMapper.toEntity(dto);
        Produto salvo = pRepository.save(produto);

        return produtoMapper.toListarDto(salvo);
    }

    public List<ProdutoListarDto> listar() {
        return pRepository.findAll()
                .stream()
                .map(produtoMapper::toListarDto)
                .toList();
    }

    public ProdutoDetalhesDto buscarPorId(Integer id) {
        Produto produto = pRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));

        return produtoMapper.toDetalhesDto(produto);
    }

    public void deletar(Integer id) {
        if (!pRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Produto não encontrado");
        }
        pRepository.deleteById(id);
    }

    public ProdutoDetalhesDto atualizar(Integer id, ProdutoCadastrarDto dto) {

        Produto produtoExistente = pRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));

        produtoExistente.setNome(dto.getNome());
        produtoExistente.setTipoProduto(dto.getTipoProduto());
        produtoExistente.setPrecoKg(dto.getPrecoKg());

        Produto atualizado = pRepository.save(produtoExistente);

        return produtoMapper.toDetalhesDto(atualizado);
    }

    public List<ProdutoListarDto> listarPorPrecoMaior() {

        List<Produto> produtos = pRepository.findAll();

        produtos.sort((a, b) -> Double.compare(b.getPrecoKg(), a.getPrecoKg()));

        return produtos.stream()
                .map(produtoMapper::toListarDto)
                .toList();
    }
}
