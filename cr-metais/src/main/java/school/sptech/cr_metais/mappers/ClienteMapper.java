package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.Cliente.ClienteCadastroDTO;
import school.sptech.cr_metais.dto.Cliente.ClienteResponseDTO;
import school.sptech.cr_metais.entity.Cliente;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteCadastroDTO dto) {
        Cliente c = new Cliente();
        c.setCnpj(dto.getCnpj());
        c.setEndereco(dto.getEndereco());
        c.setRazao_social(dto.getRazao_social());
        c.setTabelaPreco(dto.getTabelaPreco());
        c.setTel_contato(dto.getTel_contato());
        return c;
    }

    public ClienteResponseDTO toResponseDTO(Cliente c) {
        return new ClienteResponseDTO(
                c.getId_cliente(),
                c.getCnpj(),
                c.getRazao_social(),
                c.getTel_contato()
        );
    }
}
