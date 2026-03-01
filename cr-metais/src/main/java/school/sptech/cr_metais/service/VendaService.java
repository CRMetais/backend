package school.sptech.cr_metais.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.cr_metais.dto.ItemPedidoCompra.ItemPedidoCompraRequestDto;
import school.sptech.cr_metais.dto.Venda.VendaCadastroDTO;
import school.sptech.cr_metais.dto.Venda.VendaResponseDTO;
import school.sptech.cr_metais.entity.Cliente;
import school.sptech.cr_metais.entity.Estoque;
import school.sptech.cr_metais.entity.ItemPedidoVenda;
import school.sptech.cr_metais.entity.Produto;
import school.sptech.cr_metais.entity.Venda;
import school.sptech.cr_metais.exception.EntidadeInvalidaException;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.VendaMapper;
import school.sptech.cr_metais.repository.ClienteRepository;
import school.sptech.cr_metais.repository.EstoqueRepository;
import school.sptech.cr_metais.repository.ItemPedidoVendaRepository;
import school.sptech.cr_metais.repository.ProdutoRepository;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;
import school.sptech.cr_metais.repository.VendaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    private static final Logger log = LoggerFactory.getLogger(VendaService.class);
    private final VendaRepository vRepository;
    private final VendaMapper vendaMapper;
    private final TabelaPrecoRepository tabelaPrecoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    private final ItemPedidoVendaRepository itemPedidoVendaRepository;

    public VendaService(VendaRepository vRepository,
                        VendaMapper vendaMapper,
                        TabelaPrecoRepository tabelaPrecoRepository,
                        ClienteRepository clienteRepository,
                        ProdutoRepository produtoRepository,
                        EstoqueRepository estoqueRepository,
                        ItemPedidoVendaRepository itemPedidoVendaRepository) {
        this.vRepository = vRepository;
        this.vendaMapper = vendaMapper;
        this.tabelaPrecoRepository = tabelaPrecoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
        this.itemPedidoVendaRepository = itemPedidoVendaRepository;
    }

    @Transactional
    public Venda cadastrar(VendaCadastroDTO dto) {
        // 1. Converte DTO para Entidade (certifique-se que o Mapper agora preencha a List<ItemPedidoVenda>)
        Venda novaVenda = vendaMapper.toEntity(dto);

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        novaVenda.setFkCliente(cliente);

        if (novaVenda.getItens() != null) {
            for (ItemPedidoVenda item : novaVenda.getItens()) {
                item.setVenda(novaVenda);

                Estoque estoque = estoqueRepository.findFirstByProduto(item.getProduto())
                        .orElse(null);

                if (estoque != null) {
                    if (estoque.getQuantidadeDisponivel() < item.getPesoKg()) {
                        throw new RuntimeException("Estoque insuficiente: " + item.getProduto().getNome());
                    }
                    estoque.setQuantidadeDisponivel((int) (estoque.getQuantidadeDisponivel() - item.getPesoKg()));
                    estoqueRepository.save(estoque);
                }
            }
        }

        // 3. Salva a venda (o cascade salvará os itens automaticamente)
        return vRepository.save(novaVenda);
    }

    public List<VendaResponseDTO> listar() {
        return vRepository.findAll()
                .stream()
                .map(vendaMapper::toResponseDTO)
                .toList();
    }

    public VendaResponseDTO buscarPorId(Integer id) {
        Venda venda  = vRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Venda não encontrado"));

        return vendaMapper.toResponseDTO(venda);
    }

    public void deletar(Integer id) {
        if (!vRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Venda não encontrada");
        }
        vRepository.deleteById(id);
    }

}
