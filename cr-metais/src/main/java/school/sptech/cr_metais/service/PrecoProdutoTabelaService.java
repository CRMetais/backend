package school.sptech.cr_metais.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.cr_metais.dto.PrecoProdutoTabela.PrecoProdutoTabelaResponseDto;
import school.sptech.cr_metais.dto.TabelaPreco.SalvarPrecosRequestDto;
import school.sptech.cr_metais.entity.PrecoProdutoTabela;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.exception.EntidadeNulaException;
import school.sptech.cr_metais.exception.EntidadeValorAbaixoDeZeroException;
import school.sptech.cr_metais.mappers.PrecoProdutoTabelaMapper;
import school.sptech.cr_metais.repository.PrecoProdutoTabelaRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;

import java.time.LocalDate;
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

        if (precoProdutoTabelaPraCadastro.getPrecoProduto() == null){
            throw new EntidadeNulaException("O preço do produto não pode ser nulo");
        }

        if (precoProdutoTabelaPraCadastro.getPrecoProduto() <= 0){
            throw new EntidadeValorAbaixoDeZeroException("O preço do produto deve ser maior que zero");
        }

        TabelaPreco tabelaPreco = tabelaPrecoOpt.get();
        Produto produto = produtoOpt.get();

        precoProdutoTabelaPraCadastro.setTabelaPreco(tabelaPreco);
        precoProdutoTabelaPraCadastro.setProduto(produto);

        PrecoProdutoTabela precoProdutoTabelaRegistrado = precoProdutoTabelaRepository.save(precoProdutoTabelaPraCadastro);
        return precoProdutoTabelaRegistrado;
    }

    public List<PrecoProdutoTabelaResponseDto> listar(Long tabelaPrecoId, Long produtoId){
        List<PrecoProdutoTabela> precos = precoProdutoTabelaRepository.buscarPrecos(tabelaPrecoId, produtoId);
        return PrecoProdutoTabelaMapper.toResponse(precos);
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

    @Transactional
    public void salvarPrecosEmLote(SalvarPrecosRequestDto request) {
        try {
            LocalDate hoje = LocalDate.now();

            TabelaPreco tabelaAtiva = tabelaPrecoRepository
                    .findByNomeTabelaAndDataFimValidadeIsNull(request.getNomeTabela())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException(
                            "Tabela ativa não encontrada: " + request.getNomeTabela()
                    ));

            tabelaAtiva.setDataFimValidade(hoje);
            tabelaPrecoRepository.save(tabelaAtiva);

            double novaVersao = Math.round((tabelaAtiva.getVersao() + 0.1) * 10.0) / 10.0;

            TabelaPreco novaTabela = new TabelaPreco();
            novaTabela.setNomeTabela(tabelaAtiva.getNomeTabela());
            novaTabela.setTipo(tabelaAtiva.getTipo());
            novaTabela.setAtiva(tabelaAtiva.getAtiva());
            novaTabela.setVersao(novaVersao);
            novaTabela.setDataInicioValidade(hoje);
            novaTabela.setDataFimValidade(null);
            TabelaPreco tabelaSalva = tabelaPrecoRepository.save(novaTabela);

            for (SalvarPrecosRequestDto.ItemPrecoDto item : request.getItens()) {
                if (item.getPreco() == null || item.getPreco() <= 0) continue;

                Produto produto = produtoRepository
                        .findByNome(item.getNomeProduto())
                        .orElseThrow(() -> new EntidadeNaoEncontradaException(
                                "Produto não encontrado: " + item.getNomeProduto()
                        ));

                PrecoProdutoTabela registro = new PrecoProdutoTabela();
                registro.setProduto(produto);
                registro.setTabelaPreco(tabelaSalva);
                registro.setPrecoProduto(item.getPreco());
                precoProdutoTabelaRepository.save(registro);
            }

        } catch (Exception e) {
            // Log completo do erro real
            e.printStackTrace();
            throw e;
        }
    }

}
