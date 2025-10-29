package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.FornecedorCadastroDTO;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.exception.EntidadeConflitoException;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.FornecedorMapper;
import school.sptech.cr_metais.repository.FornecedorRepository;
import school.sptech.cr_metais.service.factory.ValidacaoFornecedorStrategyFactory;
import school.sptech.cr_metais.service.strategy.ValidacaoCadastroFornecedorStrategy;

import java.util.ArrayList;
import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fRepository;
    private final ValidacaoFornecedorStrategyFactory strategyFactory;
    private final FornecedorMapper fornecedorMapper;

    public FornecedorService(FornecedorRepository fRepository,
                             ValidacaoFornecedorStrategyFactory strategyFactory,
                             FornecedorMapper fornecedorMapper) {
        this.fRepository = fRepository;
        this.strategyFactory = strategyFactory;
        this.fornecedorMapper = fornecedorMapper;
    }

    public Fornecedor cadastrar(FornecedorCadastroDTO dto) {

        ValidacaoCadastroFornecedorStrategy strategy = strategyFactory.getStrategy(dto.getTipoFornecedor());

        strategy.validarConflitos(dto);

        if (dto.getApelido() != null && !dto.getApelido().isBlank() && fRepository.existsByApelido(dto.getApelido())) {
            throw new EntidadeConflitoException("Conflito no campo Apelido");
        }

        Fornecedor novoFornecedor = fornecedorMapper.toEntity(dto);

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
}