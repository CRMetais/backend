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
        c.setRazaoSocial(dto.getRazaoSocial());
        c.setTelContato(dto.getTelContato());
        return c;
    }

    public ClienteResponseDTO toResponseDTO(Cliente c) {
        return new ClienteResponseDTO(
                c.getIdCliente(),
                c.getCnpj(),
                c.getRazaoSocial(),
                c.getTelContato()
        );
    }

}