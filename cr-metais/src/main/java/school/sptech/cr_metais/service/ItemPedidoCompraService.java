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
import java.util.Optional;

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

    public ItemPedidoCompra cadastrar(ItemPedidoCompra itemParaCadastro, Integer idCompra, Integer idProduto){
        Optional<Compra> compraOpt = compraRepository.findById(idCompra);
        Optional<Produto> produtoOpt = produtoRepository.findById(idProduto);

        if (compraOpt.isEmpty()){
            throw new EntidadeNaoEncontradaException("Compra não encontrada");
        }

        if (produtoOpt.isEmpty()){
            throw new EntidadeNaoEncontradaException("Produto não encontrado");
        }

        Compra compra = compraOpt.get();
        Produto produto = produtoOpt.get();

        itemParaCadastro.setCompra(compra);
        itemParaCadastro.setProduto(produto);

        ItemPedidoCompra itemRegistrado = itemRepository.save(itemParaCadastro);
        return itemRegistrado;
    }

    public List<ItemPedidoCompra> listar(){
        return itemRepository.findAll();
    }

    public ItemPedidoCompra buscarPorId(Integer id){
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Item não encontrado"));
    }

    public void deletarPorId(Integer id){
        Boolean existe = itemRepository.existsById(id);

        if (!existe){
            throw new EntidadeNaoEncontradaException("Item não encontrado");
        }

        itemRepository.deleteById(id);
    }

    public ItemPedidoCompra atualizar(ItemPedidoCompra item){

        if (!itemRepository.existsById(item.getId())){
            throw new EntidadeNaoEncontradaException("Item não encontrado");
        }

        return itemRepository.save(item);
    }

}
