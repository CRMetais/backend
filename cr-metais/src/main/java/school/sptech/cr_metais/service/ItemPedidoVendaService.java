package school.sptech.cr_metais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.ItemPedidoVenda.ItemPedidoVendaCadastroDto;
import school.sptech.cr_metais.dto.ItemPedidoVenda.ItemPedidoVendaResponseDto;
import school.sptech.cr_metais.entity.ItemPedidoVenda;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.entity.Venda;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.ItemPedidoVendaMapper;
import school.sptech.cr_metais.repository.ItemPedidoVendaRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.repository.VendaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoVendaService {

    @Autowired
    private final ItemPedidoVendaRepository itemVendaRepository;
    private final ItemPedidoVendaMapper itemVendaMapper;
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    public ItemPedidoVendaService(ItemPedidoVendaRepository itemVendaRepository, ItemPedidoVendaMapper itemVendaMapper, VendaRepository vendaRepository, ProdutoRepository produtoRepository) {
        this.itemVendaRepository = itemVendaRepository;
        this.itemVendaMapper = itemVendaMapper;
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
    }

    public ItemPedidoVendaResponseDto cadastrar (ItemPedidoVendaCadastroDto dto){

        ItemPedidoVenda itemPedidoVenda = itemVendaMapper.toEntity(dto);

        Venda venda = vendaRepository.findById(dto.getFk_venda())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Venda não encontrada"));

        Produto produto = produtoRepository.findById(dto.getFk_produto())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));

        itemPedidoVenda.setVenda(venda);
        itemPedidoVenda.setProduto(produto);

        itemVendaRepository.save(itemPedidoVenda);

        return itemVendaMapper.toDto(itemPedidoVenda);
    }

    public List<ItemPedidoVendaResponseDto> listar() {
        return itemVendaRepository.findAll()
                .stream()
                .map(itemVendaMapper::toDto)
                .toList();
    }

    public ItemPedidoVendaResponseDto buscarPorId(Integer id) {
        ItemPedidoVenda itemPedidoVendaProcurado = itemVendaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Item não encontrado"));

        return itemVendaMapper.toDto(itemPedidoVendaProcurado);
    }

    public void deletar(Integer id) {
        if (!itemVendaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Item não encontrado");
        }
        itemVendaRepository.deleteById(id);
    }

    public ItemPedidoVendaResponseDto atualizar(Integer id, ItemPedidoVendaCadastroDto dto) {

        ItemPedidoVenda itemParaAtualizar = itemVendaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Item não encontrado"));

        itemParaAtualizar.setPrecoUnitario(dto.getPrecoUnitario());
        itemParaAtualizar.setPesoKg(dto.getPesoKg());

        Venda venda = vendaRepository.findById(dto.getFk_venda())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Venda não encontrada"));

        Produto produto = produtoRepository.findById(dto.getFk_produto())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));

        itemParaAtualizar.setProduto(produto);
        itemParaAtualizar.setVenda(venda);

        ItemPedidoVenda itemPedidoVendaAtualizado = itemVendaRepository.save(itemParaAtualizar);

        return itemVendaMapper.toDto(itemPedidoVendaAtualizado);
    }
}



