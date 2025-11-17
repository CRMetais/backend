package school.sptech.cr_metais.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Cliente.ClienteCadastroDTO;
import school.sptech.cr_metais.dto.Cliente.ClienteResponseDTO;
import school.sptech.cr_metais.entity.Cliente;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.mappers.ClienteMapper;
import school.sptech.cr_metais.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository cRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository cRepository, ClienteMapper clienteMapper) {
        this.cRepository = cRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteResponseDTO cadastrar(@Valid ClienteCadastroDTO dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        cRepository.save(cliente);
        return clienteMapper.toResponseDTO(cliente);
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
        clienteExistente.setEndereco(dto.getEndereco());
        clienteExistente.setTabelaPreco(dto.getTabelaPreco());

        cRepository.save(clienteExistente);

        return clienteMapper.toResponseDTO(clienteExistente);
    }
}
