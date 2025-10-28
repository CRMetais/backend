package school.sptech.cr_metais.mappers;

import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.FornecedorCadastroDTO;
import school.sptech.cr_metais.entity.Fornecedor;

@Component
public class FornecedorMapper {

    public Fornecedor toEntity(FornecedorCadastroDTO dto) {
        if (dto == null) {
            return null;
        }
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.getNome());
        fornecedor.setDocumento(dto.getDocumento());
        fornecedor.setTipo(dto.getTipoFornecedor());
        fornecedor.setTelefone(dto.getTelefone());
        fornecedor.setApelido(dto.getApelido());

        return fornecedor;
    }

}