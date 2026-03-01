package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Fornecedor.FornecedorCadastroDto;
import school.sptech.cr_metais.dto.Fornecedor.FornecedorTopRendimentoDto;
import school.sptech.cr_metais.entity.Endereco;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.exception.EntidadeConflitoException;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.FornecedorMapper;
import school.sptech.cr_metais.repository.EnderecoRepository;
import school.sptech.cr_metais.repository.FornecedorRepository;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;
import school.sptech.cr_metais.service.factory.ValidacaoFornecedorStrategyFactory;
import school.sptech.cr_metais.service.strategy.ValidacaoCadastroFornecedorStrategy;

import java.util.ArrayList;
import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fRepository;
    private final ValidacaoFornecedorStrategyFactory strategyFactory;
    private final FornecedorMapper fornecedorMapper;
    private final EnderecoRepository enderecoRepository;
    private final TabelaPrecoRepository tabelaPrecoRepository;

    public FornecedorService(FornecedorRepository fRepository, ValidacaoFornecedorStrategyFactory strategyFactory, FornecedorMapper fornecedorMapper, EnderecoRepository enderecoRepository, TabelaPrecoRepository tabelaPrecoRepository) {
        this.fRepository = fRepository;
        this.strategyFactory = strategyFactory;
        this.fornecedorMapper = fornecedorMapper;
        this.enderecoRepository = enderecoRepository;
        this.tabelaPrecoRepository = tabelaPrecoRepository;
    }

    public Fornecedor cadastrar(FornecedorCadastroDto dto) {

        ValidacaoCadastroFornecedorStrategy strategy = strategyFactory.getStrategy(dto.getTipoFornecedor());

        strategy.validarConflitos(dto);

        if (dto.getApelido() != null && !dto.getApelido().isBlank() && fRepository.existsByApelido(dto.getApelido())) {
            throw new EntidadeConflitoException("Conflito no campo Apelido");
        }

        Fornecedor novoFornecedor = fornecedorMapper.toEntity(dto);

        Endereco endereco = enderecoRepository.findById(dto.getIdEndereco())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado"));

        TabelaPreco tabela = tabelaPrecoRepository.findById(dto.getIdTabelaPreco())
             .orElseThrow(() -> new EntidadeNaoEncontradaException("Tabela de preço não encontrada"));
             novoFornecedor.setTabelaPreco(tabela);

        novoFornecedor.setEndereco(endereco);

        return fRepository.save(novoFornecedor);
    }

    public List<Fornecedor> listar(){
        return fRepository.findAll();
    }

    public void deletar(Integer id){
        if (!fRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Fornecedor não encontrado");
        }
        fRepository.deleteById(id);
    }

    public Fornecedor buscarPorId(Integer id){
        return fRepository.findById(id)
                .orElseThrow(
                        ()-> new
                                EntidadeNaoEncontradaException
                                ("Fornecedor não encontrado")
                );
    }

    public Fornecedor atualizar(Integer id, Fornecedor fornecedor){
        Fornecedor fornecedorAtt = fRepository.findById(id)
                .orElseThrow(
                        ()-> new EntidadeNaoEncontradaException("Fornecedor não encontrado")
                );

        fornecedorAtt.setNome(fornecedor.getNome());
        fornecedorAtt.setDocumento(fornecedor.getDocumento());
        fornecedorAtt.setTipo(fornecedor.getTipo());
        fornecedorAtt.setTelefone(fornecedor.getTelefone());
        fornecedorAtt.setApelido(fornecedor.getApelido());

        return fRepository.save(fornecedorAtt);
    }

    public List<Fornecedor> listarInversoRecursivo() {
        List<Fornecedor> fornecedores = listar();
        return inverterListaRecursivamente(fornecedores, 0);
    }

    private List<Fornecedor> inverterListaRecursivamente(List<Fornecedor> lista, int indice) {
        if (indice == lista.size()) {
            return new ArrayList<>();
        }

        List<Fornecedor> resultado = inverterListaRecursivamente(lista, indice + 1);
        resultado.add(lista.get(indice));

        return resultado;
    }

    public List<FornecedorTopRendimentoDto> listarTop10PorRendimento() {
        List<Object[]> resultadoQuery = fRepository.buscarTop10FornecedoresPorRendimento();
        List<FornecedorTopRendimentoDto> response = new ArrayList<>();

        for (Object[] linha : resultadoQuery) {
            FornecedorTopRendimentoDto dto = new FornecedorTopRendimentoDto();
            dto.setIdFornecedor(((Number) linha[0]).intValue());
            dto.setNome((String) linha[1]);
            dto.setApelido((String) linha[2]);
            dto.setTotalRendimento(linha[3] == null ? 0D : ((Number) linha[3]).doubleValue());
            response.add(dto);
        }

        return response;
    }
}