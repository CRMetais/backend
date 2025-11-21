package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Compra.CompraCadastroDto;
import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.CompraMapper;
import school.sptech.cr_metais.repository.CompraRepository;
import school.sptech.cr_metais.repository.FornecedorRepository;

import java.util.List;

@Service
public class CompraService {

    final CompraRepository compraRepository;
    final CompraMapper compraMapper;
    final FornecedorRepository fornecedorRepository;

    public CompraService(CompraRepository compraRepository, CompraMapper compraMapper, FornecedorRepository fornecedorRepository) {
        this.compraRepository = compraRepository;
        this.compraMapper = compraMapper;
        this.fornecedorRepository = fornecedorRepository;
    }

    public Compra cadastrar(CompraCadastroDto dto){

        Compra compra = compraMapper.toEntity(dto);

        Fornecedor fornecedor = fornecedorRepository.findById(dto.getIdFornecedor())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fornecedor não encontrado"));
        compra.setFornecedor(fornecedor);

        return compraRepository.save(compra);
    }

    public List<Compra> listar(){

        return compraRepository.findAll();

    }

}
