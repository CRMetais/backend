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
import school.sptech.cr_metais.exception.EntidadeInvalidaException;
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

    private ClienteResponseDTO mapearParaResponseDTO(Cliente cliente) {
        ClienteResponseDTO.TabelaPrecoResDTO tabelaDTO = null;
        if (cliente.getTabelaPreco() != null) {
            tabelaDTO = new ClienteResponseDTO.TabelaPrecoResDTO(
                    cliente.getTabelaPreco().getIdTabela(),
                    cliente.getTabelaPreco().getNomeTabela(),
                    cliente.getTabelaPreco().getTipo() != null ? cliente.getTabelaPreco().getTipo().name() : null,
                    cliente.getTabelaPreco().getVersao()
            );
        }

        ClienteResponseDTO.EnderecoResDTO enderecoDTO = null;
        if (cliente.getEndereco() != null) {
            enderecoDTO = new ClienteResponseDTO.EnderecoResDTO(
                    cliente.getEndereco().getIdEndereco(),
                    cliente.getEndereco().getLogradouro(),
                    cliente.getEndereco().getNumero(),
                    cliente.getEndereco().getBairro(),
                    cliente.getEndereco().getCidade(),
                    cliente.getEndereco().getEstado(),
                    cliente.getEndereco().getCep(),
                    cliente.getEndereco().getComplemento()
            );
        }

        return new ClienteResponseDTO(
                cliente.getIdCliente(),
                cliente.getRazaoSocial(),
                cliente.getCnpj(),
                cliente.getTelContato(),
                tabelaDTO,
                enderecoDTO,
                cliente.getIe()
        );
    }

    public Cliente cadastrar(ClienteCadastroDTO dto) {
        if (dto.getCnpj() == null || dto.getCnpj().length() < 14) {
            throw new EntidadeInvalidaException("CNPJ inválido");
        }

        Cliente cliente = clienteMapper.toEntity(dto);

        Endereco endereco = enderecoRepository.findById(dto.getIdEndereco())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado"));

        TabelaPreco tabelaPreco = tabelaPrecoRepository.findById(dto.getIdTabelaPreco())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tabela de preço não encontrada"));

        cliente.setEndereco(endereco);
        cliente.setTabelaPreco(tabelaPreco);
        cliente.setAtivo(true); // Garante que nasce ativo

        return cRepository.save(cliente);
    }


    public List<ClienteResponseDTO> listar() {
        return cRepository.findByAtivoTrue()
                .stream()
                .map(this::mapearParaResponseDTO)
                .toList();
    }

    public List<ClienteResponseDTO> listarClientes() {
        List<Cliente> clientes = cRepository.findByAtivoTrue();
        return clientes.stream()
                .map(this::mapearParaResponseDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Integer id) {
        Cliente cliente = cRepository.findByIdClienteAndAtivoTrue(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        return mapearParaResponseDTO(cliente);
    }

    public void deletar(Integer id) {
        int linhasAfetadas = cRepository.inativarCliente(id);

        if (linhasAfetadas == 0) {
            throw new EntidadeNaoEncontradaException("Cliente não encontrado");
        }
    }

    public ClienteResponseDTO atualizar(Integer id, ClienteCadastroDTO dto) {
        Cliente clienteExistente = cRepository.findByIdClienteAndAtivoTrue(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));

        clienteExistente.setCnpj(dto.getCnpj());
        clienteExistente.setRazaoSocial(dto.getRazaoSocial());
        clienteExistente.setTelContato(dto.getTelContato());

        if (dto.getIdEndereco() != null) {
            Endereco endereco = enderecoRepository.findById(dto.getIdEndereco())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado"));
            clienteExistente.setEndereco(endereco);
        }

        if (dto.getIdTabelaPreco() != null) {
            TabelaPreco tabela = tabelaPrecoRepository.findById(dto.getIdTabelaPreco())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Tabela de preço não encontrada"));
            clienteExistente.setTabelaPreco(tabela);
        }

        cRepository.save(clienteExistente);

        return mapearParaResponseDTO(clienteExistente);
    }
}
