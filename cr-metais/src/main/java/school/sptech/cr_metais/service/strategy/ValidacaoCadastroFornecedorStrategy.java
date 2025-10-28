package school.sptech.cr_metais.service.strategy;

import school.sptech.cr_metais.dto.FornecedorCadastroDTO;
import school.sptech.cr_metais.entity.TipoFornecedor;

public interface ValidacaoCadastroFornecedorStrategy {

    void validarConflitos(FornecedorCadastroDTO dto);

    TipoFornecedor getTipo();
}