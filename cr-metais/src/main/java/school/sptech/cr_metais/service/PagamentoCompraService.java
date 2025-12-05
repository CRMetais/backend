package school.sptech.cr_metais.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraCadastroDto;
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraResponseDto;
import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.ContaPagamento;
import school.sptech.cr_metais.entity.PagamentoCompra;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.PagamentoCompraMapper;
import school.sptech.cr_metais.repository.CompraRepository;
import school.sptech.cr_metais.repository.ContaPagamentoRepository;
import school.sptech.cr_metais.repository.PagamentoCompraRepository;

import java.util.List;

@Service
public class PagamentoCompraService {

    final PagamentoCompraMapper mapper;
    final PagamentoCompraRepository repository;
    final ContaPagamentoRepository contaPagamentoRepository;
    final CompraRepository compraRepository;

    public PagamentoCompraService(PagamentoCompraMapper mapper, PagamentoCompraRepository repository, ContaPagamentoRepository contaPagamentoRepository, CompraRepository compraRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.contaPagamentoRepository = contaPagamentoRepository;
        this.compraRepository = compraRepository;
    }

    public PagamentoCompraResponseDto cadastrar(@Valid PagamentoCompraCadastroDto dto) {

        PagamentoCompra pagamento = PagamentoCompraMapper.toEntity(dto);

        Compra compra = compraRepository.findById(dto.getIdCompra())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pagamento Compra não encontrada"));
        pagamento.setCompra(compra);

        ContaPagamento conta = contaPagamentoRepository.findById(dto.getIdContaPagamento())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pagamento Compra de pagamento não encontrada"));
        pagamento.setContaPagamento(conta);

        PagamentoCompra salvo = repository.save(pagamento);

        return PagamentoCompraMapper.toResponse(salvo);
    }

    public  List<PagamentoCompraResponseDto> listar() {
        return repository.findAll()
                .stream()
                .map(PagamentoCompraMapper::toResponse)
                .toList();
    }

    public PagamentoCompraResponseDto buscarPorId(Integer id) {

        PagamentoCompra pagamento = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pagamento não encontrado"));

        return PagamentoCompraMapper.toResponse(pagamento);
    }

    public void deletar(Integer id) {

        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Pagamento não encontrado");
        }
        repository.deleteById(id);
    }

}
