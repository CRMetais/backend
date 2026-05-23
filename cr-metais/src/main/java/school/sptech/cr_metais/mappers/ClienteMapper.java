package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.Cliente.ClienteCadastroDTO;
import school.sptech.cr_metais.dto.Cliente.ClienteResponseDTO;
import school.sptech.cr_metais.entity.Cliente;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteCadastroDTO dto) {
        if (dto == null) return null;
        Cliente c = new Cliente();
        c.setCnpj(dto.getCnpj());
        c.setRazaoSocial(dto.getRazaoSocial());
        c.setTelContato(dto.getTelContato());
        return c;
    }

    public ClienteResponseDTO toResponseDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        // 1. Mapeia o sub-DTO da Tabela de Preço de forma segura
        ClienteResponseDTO.TabelaPrecoResDTO tabelaDTO = null;
        if (cliente.getTabelaPreco() != null) {
            tabelaDTO = new ClienteResponseDTO.TabelaPrecoResDTO(
                    cliente.getTabelaPreco().getIdTabela(),
                    cliente.getTabelaPreco().getNomeTabela(),
                    cliente.getTabelaPreco().getTipo() != null ? cliente.getTabelaPreco().getTipo().name() : null,
                    cliente.getTabelaPreco().getVersao()
            );
        }

        // 2. Mapeia o sub-DTO do Endereço de forma segura
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

        // 3. Retorna o DTO principal utilizando o novo construtor completo com 6 parâmetros
        return new ClienteResponseDTO(
                cliente.getIdCliente(),
                cliente.getRazaoSocial(),
                cliente.getCnpj(),
                cliente.getTelContato(),
                tabelaDTO,
                enderecoDTO
        );
    }
}