package school.sptech.cr_metais.service;

import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraRequestDto;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraResponseDto;
import school.sptech.cr_metais.entity.Compra;
import school.sptech.cr_metais.entity.ItemPedidoCompra;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.ItemPedidoCompraMapper;
import school.sptech.cr_metais.repository.CompraRepository;
import school.sptech.cr_metais.repository.ItemPedidoCompraRepository;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.repository.ProdutoRepository;

import java.util.List;

@Service
public class ItemPedidoCompraService {

    private final ItemPedidoCompraRepository itemRepository;
    private final CompraRepository compraRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoCompraMapper mapper;

    public ItemPedidoCompraService(
            ItemPedidoCompraRepository itemRepository,
            CompraRepository compraRepository,
            ProdutoRepository produtoRepository,
            ItemPedidoCompraMapper mapper
    ){
        this.itemRepository = itemRepository;
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
        this.mapper = mapper;

    }

    public ItemPedidoCompraResponseDto salvar(ItemPedidoCompraRequestDto dto){
        Compra compra = compraRepository.findById(dto.getIdCompra())
                .orElseThrow(() -> new RuntimeException("Compra não encontrada!"));

        Produto produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontado!"));

        ItemPedidoCompra item = mapper.toEntity(dto, compra, produto);

        ItemPedidoCompra salvo = itemRepository.save(item);

        return mapper.toResponseDTO(salvo);
    }

    public List<ItemPedidoCompraResponseDto> listarTodos(){
        return itemRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public ItemPedidoCompraResponseDto buscarPorId(Integer id){
        ItemPedidoCompra item = itemRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Item de pedido não encontrado"));

        return mapper.toResponseDTO(item);
    }

    public void deletar(Integer id){
        if (!itemRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Item de pedido não encontrado");
        }
        itemRepository.deleteById(id);
    }

    public ItemPedidoCompraResponseDto atualizar(Integer id, ItemPedidoCompraRequestDto dto){

        ItemPedidoCompra itemParaAtualizar = itemRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Item de pedido não encontrado"));

        Compra compra = compraRepository.findById(dto.getIdCompra())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Compra não encontrada"));

        Produto produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não encontrado"));


        itemParaAtualizar.setCompra(compra);
        itemParaAtualizar.setProduto(produto);
        itemParaAtualizar.setPesoKg(dto.getPesoKg());
        itemParaAtualizar.setPrecoUnitario(dto.getPrecoUnitario());

        ItemPedidoCompra itemAtualizado = itemRepository.save(itemParaAtualizar);

        return mapper.toResponseDTO(itemAtualizado);
    }

}
