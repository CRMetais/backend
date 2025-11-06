package school.sptech.cr_metais.service.strategy;

import school.sptech.cr_metais.dto.Fornecedor.FornecedorCadastroDto;
import school.sptech.cr_metais.entity.TipoFornecedor;

public interface ValidacaoCadastroFornecedorStrategy {

    void validarConflitos(FornecedorCadastroDto dto);

    TipoFornecedor getTipo();
}