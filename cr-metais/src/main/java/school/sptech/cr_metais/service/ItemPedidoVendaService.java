package school.sptech.cr_metais.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.cr_metais.dto.ItemPedidoVenda.ItemPedidoVendaCadastroDto;
import school.sptech.cr_metais.dto.ItemPedidoVenda.ItemPedidoVendaResponseDto;
import school.sptech.cr_metais.entity.Estoque;
import school.sptech.cr_metais.entity.ItemPedidoVenda;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.entity.Venda;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.ItemPedidoVendaMapper;
import school.sptech.cr_metais.repository.EstoqueRepository;
import school.sptech.cr_metais.repository.ItemPedidoVendaRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.repository.VendaRepository;

import java.util.List;

@Service
public class ItemPedidoVendaService {

    private final ItemPedidoVendaRepository itemVendaRepository;
    private final ItemPedidoVendaMapper itemVendaMapper;
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;

    public ItemPedidoVendaService(ItemPedidoVendaRepository itemVendaRepository,
                                  ItemPedidoVendaMapper itemVendaMapper,
                                  VendaRepository vendaRepository,
                                  ProdutoRepository produtoRepository,
                                  EstoqueRepository estoqueRepository) {
        this.itemVendaRepository = itemVendaRepository;
        this.itemVendaMapper = itemVendaMapper;
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @Transactional
    public ItemPedidoVendaResponseDto cadastrar(ItemPedidoVendaCadastroDto dto) {

        ItemPedidoVenda itemPedidoVenda = itemVendaMapper.toEntity(dto);

        Venda venda = vendaRepository.findById(dto.getFk_venda())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Venda não encontrada"));

        Produto produto = produtoRepository.findById(dto.getFk_produto())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));

        Estoque estoque = estoqueRepository.findFirstByProduto(produto)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Nenhum estoque cadastrado para o produto: " + produto.getNome()));

        int quantidadeVenda = dto.getPesoKg() != null ? dto.getPesoKg().intValue() : 0;
        int quantidadeAtual = estoque.getQuantidadeDisponivel() != null ? estoque.getQuantidadeDisponivel() : 0;

        int novoEstoque = quantidadeAtual - quantidadeVenda;
        estoque.setQuantidadeDisponivel(novoEstoque);
        estoqueRepository.save(estoque);

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

    @Transactional
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