package school.sptech.cr_metais.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.PagamentoCompra.PagamentoCompraCadastroDto;
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

    public PagamentoCompra cadastrar(PagamentoCompraCadastroDto dto) {

        PagamentoCompra pagamentoCompra = mapper.toEntity(dto);

        Compra compra = compraRepository.findById(dto.getIdCompra())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Compra não encontada"));
        pagamentoCompra.setCompra(compra);


        ContaPagamento contaPagamento = contaPagamentoRepository.findById(dto.getIdContaPagamento())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Conta de pagamento não encontada"));
        pagamentoCompra.setContaPagamento(contaPagamento);

        return repository.save(pagamentoCompra);

    }

    public List<PagamentoCompra> listar(){

        return repository.findAll();

    }

}
