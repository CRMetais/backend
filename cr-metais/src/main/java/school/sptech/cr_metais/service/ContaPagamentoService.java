package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.ContaPagamento.ContaPagamentoCadastroDto;
import school.sptech.cr_metais.dto.ContaPagamento.ContaPagamentoResponseDto;
import school.sptech.cr_metais.entity.ContaPagamento;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.ContaPagamentoMapper;
import school.sptech.cr_metais.repository.ContaPagamentoRepository;
import school.sptech.cr_metais.repository.FornecedorRepository;

import java.util.List;

@Service
public class ContaPagamentoService {

    private final ContaPagamentoRepository contaPagamentoRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ContaPagamentoMapper contaPagamentoMapper;

    public ContaPagamentoService(ContaPagamentoRepository contaPagamentoRepository,
                                 FornecedorRepository fornecedorRepository,
                                 ContaPagamentoMapper contaPagamentoMapper) {
        this.contaPagamentoRepository = contaPagamentoRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.contaPagamentoMapper = contaPagamentoMapper;
    }


    public ContaPagamentoResponseDto cadastrar(ContaPagamentoCadastroDto dto) {

        Fornecedor fornecedor = fornecedorRepository.findById(dto.getIdFornecedor())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fornecedor não encontrado"));

        ContaPagamento conta = contaPagamentoMapper.toEntity(dto);
        conta.setFornecedor(fornecedor);
        conta = contaPagamentoRepository.save(conta);

        return contaPagamentoMapper.toResponseDTO(conta);
    }

    public List<ContaPagamentoResponseDto> listar() {
        return contaPagamentoRepository.findAll()
                .stream()
                .map(contaPagamentoMapper::toResponseDTO)
                .toList();
    }

    public ContaPagamentoResponseDto buscarPorId(Integer id) {
        ContaPagamento conta = contaPagamentoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Conta de pagamento não encontrada"));
        return contaPagamentoMapper.toResponseDTO(conta);
    }

    public void deletar(Integer id) {
        if (!contaPagamentoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Conta de pagamento não encontrada");
        }
        contaPagamentoRepository.deleteById(id);
    }

    public ContaPagamentoResponseDto atualizar(Integer id, ContaPagamentoCadastroDto dto) {

        ContaPagamento contaParaAtualizar = contaPagamentoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Conta de pagamento não encontrada"));

        Fornecedor fornecedor = fornecedorRepository.findById(dto.getIdFornecedor())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fornecedor não encontrado"));

        contaParaAtualizar.setPix(dto.getPix());
        contaParaAtualizar.setBanco(dto.getBanco());
        contaParaAtualizar.setAgencia(dto.getAgencia());
        contaParaAtualizar.setConta(dto.getConta());
        contaParaAtualizar.setTipoConta(dto.getTipoConta());
        contaParaAtualizar.setChavePix(dto.getChavePix());
        contaParaAtualizar.setPertenceFornecedor(dto.getPertenceFornecedor());
        contaParaAtualizar.setNome(dto.getNome());
        contaParaAtualizar.setDocumento(dto.getDocumento());
        contaParaAtualizar.setContaAtiva(dto.getContaAtiva());
        contaParaAtualizar.setFornecedor(fornecedor);

        ContaPagamento contaAtualizada = contaPagamentoRepository.save(contaParaAtualizar);

        return contaPagamentoMapper.toResponseDTO(contaAtualizada);
    }

}
