package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.cr_metais.dto.Fornecedor.FornecedorCadastroDto;
import school.sptech.cr_metais.dto.Fornecedor.FornecedorTopRendimentoDto;
import school.sptech.cr_metais.entity.Endereco;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.entity.Usuario;
import school.sptech.cr_metais.exception.EntidadeConflitoException;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.FornecedorMapper;
import school.sptech.cr_metais.repository.*;
import school.sptech.cr_metais.service.factory.ValidacaoFornecedorStrategyFactory;
import school.sptech.cr_metais.service.strategy.ValidacaoCadastroFornecedorStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fRepository;
    private final ValidacaoFornecedorStrategyFactory strategyFactory;
    private final FornecedorMapper fornecedorMapper;
    private final EnderecoRepository enderecoRepository;
    private final TabelaPrecoRepository tabelaPrecoRepository;
    private final ContaPagamentoRepository contaPagamentoRepository;
    private final CompraRepository compraRepository;
    private final UsuarioRepository usuarioRepository;


    public FornecedorService(FornecedorRepository fRepository,
                             ValidacaoFornecedorStrategyFactory strategyFactory,
                             FornecedorMapper fornecedorMapper,
                             EnderecoRepository enderecoRepository,
                             TabelaPrecoRepository tabelaPrecoRepository,
                             ContaPagamentoRepository contaPagamentoRepository,
                             CompraRepository compraRepository,
                             UsuarioRepository usuarioRepository) {
        this.fRepository = fRepository;
        this.strategyFactory = strategyFactory;
        this.fornecedorMapper = fornecedorMapper;
        this.enderecoRepository = enderecoRepository;
        this.tabelaPrecoRepository = tabelaPrecoRepository;
        this.contaPagamentoRepository = contaPagamentoRepository;
        this.compraRepository = compraRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Fornecedor cadastrar(FornecedorCadastroDto dto) {

        System.out.println("idUsuario recebido: " + dto.getIdUsuario());

        ValidacaoCadastroFornecedorStrategy strategy = strategyFactory.getStrategy(dto.getTipoFornecedor());

        strategy.validarConflitos(dto);

        if (dto.getApelido() != null && !dto.getApelido().isBlank() && fRepository.existsByApelidoAndAtivoTrue(dto.getApelido())) {
            throw new EntidadeConflitoException("Conflito no campo Apelido");
        }

        Fornecedor novoFornecedor = fornecedorMapper.toEntity(dto);

        Endereco endereco = enderecoRepository.findById(dto.getIdEndereco())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado"));

        TabelaPreco tabela = tabelaPrecoRepository.findById(dto.getIdTabelaPreco())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tabela de preço não encontrada"));
        novoFornecedor.setTabelaPreco(tabela);

        novoFornecedor.setEndereco(endereco);

        if (dto.getIdUsuario() != null) {
            Usuario responsavel = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
            novoFornecedor.setResponsavel(responsavel);
        }

        if (dto.getIdUsuario() != null) {
            System.out.println("Buscando usuário com id: " + dto.getIdUsuario());
            Usuario responsavel = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
            System.out.println("Usuário encontrado: " + responsavel.getNome());
            novoFornecedor.setResponsavel(responsavel);
            System.out.println("Responsável setado: " + novoFornecedor.getResponsavel().getNome());
        }

        Fornecedor salvo = fRepository.save(novoFornecedor);
        System.out.println("fk_usuario após save: " + (salvo.getResponsavel() != null ? salvo.getResponsavel().getIdUsuario() : "NULL"));
        return salvo;
    }

    public List<Fornecedor> listar() {
        return fRepository.findAllByAtivoTrue();
    }

    @Transactional
    public void deletar(Integer id) {
        Fornecedor fornecedor = fRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fornecedor não encontrado"));
        fornecedor.setAtivo(false);
        fRepository.save(fornecedor);
    }

    public Fornecedor buscarPorId(Integer id) {
        return fRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fornecedor não encontrado"));
    }

    public Fornecedor atualizar(Integer id, Fornecedor fornecedor) {
        Fornecedor fornecedorAtt = fRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fornecedor não encontrado"));

        fornecedorAtt.setNome(fornecedor.getNome());
        fornecedorAtt.setDocumento(fornecedor.getDocumento());
        fornecedorAtt.setTipo(fornecedor.getTipo());
        fornecedorAtt.setTelefone(fornecedor.getTelefone());
        fornecedorAtt.setApelido(fornecedor.getApelido());

        if (fornecedor.getTabelaPreco() != null && fornecedor.getTabelaPreco().getIdTabela() != null) {
            TabelaPreco tabela = tabelaPrecoRepository.findById(fornecedor.getTabelaPreco().getIdTabela())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Tabela não encontrada"));
            fornecedorAtt.setTabelaPreco(tabela);
        }

        if (fornecedor.getResponsavel() != null && fornecedor.getResponsavel().getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(fornecedor.getResponsavel().getIdUsuario())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
            fornecedorAtt.setResponsavel(usuario);
        }

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

    public List<FornecedorTopRendimentoDto> listarTop10PorRendimento(LocalDate dataInicio, LocalDate dataFim) {
        List<Object[]> resultadoQuery = fRepository.buscarTop10FornecedoresPorRendimento(dataInicio, dataFim);
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