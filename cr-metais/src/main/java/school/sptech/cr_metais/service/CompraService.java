package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Compra.CompraCadastroDto;
import school.sptech.cr_metais.dto.Compra.CompraResponseDto;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraRequestDto;
import school.sptech.cr_metais.entity.*;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.CompraMapper;
import school.sptech.cr_metais.repository.CompraRepository;
import school.sptech.cr_metais.repository.FornecedorRepository;
import school.sptech.cr_metais.repository.PrecoProdutoTabelaRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;
    private final PrecoProdutoTabelaRepository precoProdutoTabelaRepository;

    public CompraService(CompraRepository compraRepository,
                         FornecedorRepository fornecedorRepository,
                         ProdutoRepository produtoRepository,
                         PrecoProdutoTabelaRepository precoProdutoTabelaRepository) {
        this.compraRepository = compraRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.produtoRepository = produtoRepository;
        this.precoProdutoTabelaRepository = precoProdutoTabelaRepository;
    }
        public CompraResponseDto cadastrar(CompraCadastroDto dto) {

            Fornecedor fornecedor = fornecedorRepository.findById(dto.getIdFornecedor())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Fornecedor não encontrado"));

            Compra compra = new Compra();
            compra.setFornecedor(fornecedor);
            compra.setDataCompra(dto.getDataCompra());

            if (compra.getItens() == null) {
                compra.setItens(new ArrayList<>());
            }

            for (ItemPedidoCompraRequestDto itemDto : dto.getItens()) {

                Produto produto = produtoRepository.findById(itemDto.getIdProduto())
                        .orElseThrow(() -> new EntidadeNaoEncontradaException(
                                "Produto não encontrado: " + itemDto.getIdProduto()));

                PrecoProdutoTabela precoVendaVital = precoProdutoTabelaRepository
                        .findFirstByProdutoIdProdutoAndTabelaPrecoTipoAndTabelaPrecoNomeTabelaAndTabelaPrecoAtivaTrueOrderByTabelaPrecoVersaoDesc(
                                produto.getIdProduto(),
                                TipoTabela.V,
                                "Vital"
                        )
                        .orElseThrow(() -> new EntidadeNaoEncontradaException(
                                "Preço de venda Vital não encontrado para o produto " + produto.getNome()));

                double precoCompra = itemDto.getPrecoUnitario();           // preço de COMPRA (por kg)
                double precoVenda = precoVendaVital.getPrecoProduto();    // preço de VENDA da tabela Vital
                double pesoKg = itemDto.getPesoKg();

                double rendimentoPorKg = precoVenda - precoCompra;
                double rendimentoTotal = rendimentoPorKg * pesoKg;

                ItemPedidoCompra item = new ItemPedidoCompra();
                item.setCompra(compra);
                item.setProduto(produto);
                item.setPesoKg(pesoKg);
                item.setPrecoUnitario(precoCompra);
                item.setRendimento(rendimentoTotal);

                compra.getItens().add(item);
            }
            return CompraMapper.toResponse(compraRepository.save(compra));
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
