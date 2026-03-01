package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Produto.ProdutoResponseDto;
import school.sptech.cr_metais.dto.Produto.ProdutoTopPesoVendidoDto;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.ProdutoMapper;
import school.sptech.cr_metais.repository.EstoqueRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

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

    public Produto cadastrar(Produto produtoParaCadastrar) {
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

    public List<ProdutoTopPesoVendidoDto> listarTop10PorPesoVendido() {
        List<Object[]> resultadoQuery = pRepository.buscarTop10ProdutosPorPesoVendido();
        List<ProdutoTopPesoVendidoDto> response = new ArrayList<>();

        for (Object[] linha : resultadoQuery) {
            ProdutoTopPesoVendidoDto dto = new ProdutoTopPesoVendidoDto();
            dto.setIdProduto(((Number) linha[0]).intValue());
            dto.setNome((String) linha[1]);
            dto.setTotalPesoVendido(linha[2] == null ? 0D : ((Number) linha[2]).doubleValue());
            response.add(dto);
        }

        return response;
    }

}
