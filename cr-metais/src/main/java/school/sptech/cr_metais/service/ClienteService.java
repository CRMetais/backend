package school.sptech.cr_metais.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Cliente.ClienteCadastroDTO;
import school.sptech.cr_metais.dto.Cliente.ClienteResponseDTO;
import school.sptech.cr_metais.dto.Fornecedor.FornecedorCadastroDto;
import school.sptech.cr_metais.entity.Cliente;
import school.sptech.cr_metais.entity.Endereco;
import school.sptech.cr_metais.entity.Fornecedor;
import school.sptech.cr_metais.entity.TabelaPreco;
import school.sptech.cr_metais.exception.EntidadeConflitoException;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.ClienteMapper;
import school.sptech.cr_metais.repository.ClienteRepository;
import school.sptech.cr_metais.repository.EnderecoRepository;
import school.sptech.cr_metais.repository.TabelaPrecoRepository;
import school.sptech.cr_metais.service.strategy.ValidacaoCadastroFornecedorStrategy;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository cRepository;
    private final ClienteMapper clienteMapper;
    private final EnderecoRepository enderecoRepository;
    private final TabelaPrecoRepository tabelaPrecoRepository;

    public ClienteService(ClienteRepository cRepository, ClienteMapper clienteMapper, EnderecoRepository enderecoRepository, TabelaPrecoRepository tabelaPrecoRepository) {
        this.cRepository = cRepository;
        this.clienteMapper = clienteMapper;
        this.enderecoRepository = enderecoRepository;
        this.tabelaPrecoRepository = tabelaPrecoRepository;
    }

    public Cliente cadastrar(ClienteCadastroDTO dto) {


        Cliente novoCliente = clienteMapper.toEntity(dto);

        Endereco endereco = enderecoRepository.findById(dto.getIdEndereco())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado"));

        TabelaPreco tabela = tabelaPrecoRepository.findById(dto.getIdTabelaPreco())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tabela de preço não encontrada"));
        novoCliente.setTabelaPreco(tabela);

        novoCliente.setEndereco(endereco);

        return cRepository.save(novoCliente);
    }

    public List<ClienteResponseDTO> listar() {
        return cRepository.findAll()
                .stream()
                .map(clienteMapper::toResponseDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Integer id) {
        Cliente cliente = cRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));

        return clienteMapper.toResponseDTO(cliente);
    }

    public void deletar(Integer id) {
        if (!cRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Cliente não encontrado");
        }
        cRepository.deleteById(id);
    }

    public ClienteResponseDTO atualizar(Integer id, ClienteCadastroDTO dto) {
        Cliente clienteExistente = cRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));

        clienteExistente.setCnpj(dto.getCnpj());
        clienteExistente.setRazao_social(dto.getRazao_social());
        clienteExistente.setTel_contato(dto.getTel_contato());

        cRepository.save(clienteExistente);

        return clienteMapper.toResponseDTO(clienteExistente);
    }
}
