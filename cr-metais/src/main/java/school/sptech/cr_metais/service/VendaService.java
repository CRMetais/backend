package school.sptech.cr_metais.service;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Cliente.ClienteCadastroDTO;
import school.sptech.cr_metais.dto.Venda.VendaCadastroDTO;
import school.sptech.cr_metais.dto.Venda.VendaResponseDTO;
import school.sptech.cr_metais.entity.Cliente;
import school.sptech.cr_metais.entity.Endereco;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.entity.Venda;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.ClienteMapper;
import school.sptech.cr_metais.mappers.VendaMapper;
import school.sptech.cr_metais.repository.ClienteRepository;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;
import school.sptech.cr_metais.repository.VendaRepository;

import java.util.List;

@Service
public class VendaService {

    private static final Logger log = LoggerFactory.getLogger(VendaService.class);
    private final VendaRepository vRepository;
    private final VendaMapper vendaMapper;
    private final TabelaPrecoRepository tabelaPrecoRepository;
    private final ClienteRepository clienteRepository;

    public VendaService(VendaRepository vRepository, VendaMapper vendaMapper, TabelaPrecoRepository tabelaPrecoRepository, ClienteRepository clienteRepository) {
        this.vRepository = vRepository;
        this.vendaMapper = vendaMapper;
        this.tabelaPrecoRepository = tabelaPrecoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Venda cadastrar(VendaCadastroDTO dto) {


        Venda novaVenda = vendaMapper.toEntity(dto);

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));

        TabelaPreco tabela = tabelaPrecoRepository.findById(dto.getIdTabelaPreco())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tabela de preço não encontrada"));
        novaVenda.setFkTabelaPreco(tabela);

        novaVenda.setFkCliente(cliente);

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
