package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.PrecoProdutoTabela.PrecoProdutoTabelaRequestDto;
import school.sptech.cr_metais.dto.PrecoProdutoTabela.PrecoProdutoTabelaResponseDto;
import school.sptech.cr_metais.entity.ItemPedidoCompra;
import school.sptech.cr_metais.entity.PrecoProdutoTabela;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.PrecoProdutoTabelaMapper;
import school.sptech.cr_metais.repository.PrecoProdutoTabelaRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;

import java.util.List;

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

    public PrecoProdutoTabelaResponseDto criarPrecoProduto(PrecoProdutoTabelaRequestDto dto){
        TabelaPreco tabelaPreco = tabelaPrecoRepository.findById(dto.getIdTabelaPreco())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tabela de preço não encontrada"));

        Produto produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));

        PrecoProdutoTabela precoProdutoTabela = mapper.toEntity(dto, tabelaPreco, produto);
        PrecoProdutoTabela salvo = precoProdutoTabelaRepository.save(precoProdutoTabela);

        return mapper.toResponseDto(salvo);
    }

    public List<PrecoProdutoTabelaResponseDto> listarTodos(){
        return precoProdutoTabelaRepository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }


    public PrecoProdutoTabelaResponseDto buscarPorId(Integer id){
        PrecoProdutoTabela precoProdutoTabela = precoProdutoTabelaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Preço do produto não encontrado"));

        return mapper.toResponseDto(precoProdutoTabela);
    }

    public void deletar(Integer id){
        if (!precoProdutoTabelaRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Preço do produto não encontrado");
        }

        precoProdutoTabelaRepository.deleteById(id);
    }

}
