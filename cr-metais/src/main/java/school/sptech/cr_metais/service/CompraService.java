package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Compra.CompraCadastroDto;
import school.sptech.cr_metais.dto.Compra.CompraResponseDto;
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

    public CompraResponseDto cadastrar(CompraCadastroDto dto){

        Fornecedor fornecedor = fornecedorRepository.findById(dto.getIdFornecedor())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("compra não encontrado"));

        Compra compra = compraMapper.toEntity(dto);
        compra.setFornecedor(fornecedor);
        compra = compraRepository.save(compra);

        return CompraMapper.toResponse(compra);
    }

    public List<CompraResponseDto> listar(){
        return compraRepository.findAll().stream().map(CompraMapper::toResponse).toList();
    }

    public CompraResponseDto buscarPorId(Integer id){

        Compra compra = compraRepository.findById(id)
                .orElseThrow(()-> new EntidadeNaoEncontradaException("Compra não encontrada"));
        return CompraMapper.toResponse(compra);

    }

    public void deletar(Integer id){
        if (!compraRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Compra não encontrada");
        }
        compraRepository.deleteById(id);
    }

    public CompraResponseDto atualizar(Integer id, CompraCadastroDto dto){

        Compra compraAtualizar = compraRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Compra não encontrada"));

        Fornecedor fornecedor = fornecedorRepository.findById(dto.getIdFornecedor())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fornecedor não encontrado"));

        compraAtualizar.setFornecedor(fornecedor);
        compraAtualizar.setDataCompra(dto.getDataCompra());

        Compra compraAtualzada = compraRepository.save(compraAtualizar);
        return CompraMapper.toResponse(compraAtualzada);

    }

}
